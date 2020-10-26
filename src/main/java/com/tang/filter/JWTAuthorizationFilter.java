package com.tang.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tang.entity.User;
import com.tang.exception.TokenIsExpiredException;
import com.tang.model.ElasticSearchUser;
import com.tang.util.ApplicationContextUtil;
import com.tang.util.ElasticsearchUtils;
import com.tang.util.JwtTokenUtils;
import com.tang.util.RedisUtil;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        System.out.println("调用1");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        System.out.println("调用2");
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        try {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        } catch (TokenIsExpiredException e) {
            //返回json形式的错误信息
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            String reason = "统一处理，原因：" + e.getMessage();
            response.getWriter().write(new ObjectMapper().writeValueAsString(reason));
            response.getWriter().flush();
            return;
        }
        super.doFilterInternal(request, response, chain);
    }

    // 这里从token中获取用户信息并新建一个token
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) throws TokenIsExpiredException, IOException {
        if (JwtTokenUtils.isExpiration(tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, ""))) {
            throw new TokenIsExpiredException("token超时了");
        } else {
            ApplicationContext context = ApplicationContextUtil.getContext();
            RedisUtil redisUtil = context.getBean(RedisUtil.class);
            String redisUser = (String) redisUtil.get(tokenHeader);
            System.out.println(redisUser);
            if (redisUser != null) {
                User user = JSON.parseObject(redisUser, User.class);
                String role = (String) redisUtil.get(user.getRole());
                List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);
                String username = user.getUsername();
                if (username != null) {
                    ElasticsearchUtils elasticsearchUtils = context.getBean(ElasticsearchUtils.class);

                    String s = elasticsearchUtils.existsDocument("user_hot", username);
                    if (s == null){
                        ElasticSearchUser elasticSearchUser = new ElasticSearchUser();
                        elasticSearchUser.setId(user.getId());
                        elasticSearchUser.setUsername(user.getUsername());
                        elasticSearchUser.setHotNumber(1);
                        elasticsearchUtils.addDocument("user_hot", elasticSearchUser.getUsername(), JSON.toJSONString(elasticSearchUser));
                    }else {
                        ElasticSearchUser elasticSearchUser = JSON.parseObject(s, ElasticSearchUser.class);
                        int hotNumber = elasticSearchUser.getHotNumber();
                        elasticSearchUser.setHotNumber(++hotNumber);
                        elasticsearchUtils.updateDocument("user_hot", elasticSearchUser.getUsername(), JSON.toJSONString(elasticSearchUser));
                    }

                    return new UsernamePasswordAuthenticationToken(username, null, authorities);
                }
            }
        }
        return null;

        /*String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        boolean expiration = JwtTokenUtils.isExpiration(token);
        if (expiration) {
            throw new TokenIsExpiredException("token超时了");
        } else {
            String username = JwtTokenUtils.getUsername(token);
            String role = JwtTokenUtils.getUserRole(token);
            if (username != null) {
                return new UsernamePasswordAuthenticationToken(username, null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(role)
                );
            }
        }
        return null;*/
    }
}