package com.tang.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tang.entity.Role;
import com.tang.model.RolePermissionList;
import com.tang.service.RolePermissionService;
import com.tang.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.*;

@Service
@Slf4j
public class MyInvocationSecurityMetadataSourceService implements
        FilterInvocationSecurityMetadataSource {
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private RedisUtil redisUtil;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    /*@Value("${jwt.rolePermissionTime}")
    private long rolePermissionTime;*/

    /**
     * 从数据库加载权限表中所有权限
     * @return
     */
    private String loadResourceDefine() {
        String rolePermissionList = (String) redisUtil.get("rolePermissionList");
        if (rolePermissionList == null) {
            rolePermissionList = JSON.toJSONString(rolePermissionService.rolePermissionList());
            redisUtil.set("rolePermissionList", rolePermissionList);
        }
        return rolePermissionList;
    }
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String rolePermissionString = loadResourceDefine();

        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();

        List rolePermissionList = JSON.parseObject(rolePermissionString, List.class);

        for (Object rolePermissionObject : rolePermissionList) {
            RolePermissionList rolePermission = JSON.parseObject(JSON.toJSONString(rolePermissionObject), RolePermissionList.class);
            if (antPathMatcher.match(rolePermission.getPermission(), url)) {
                List<Role> roles = rolePermission.getRoles();
                String[] roleArr = new String[roles.size()];
                for (int i = 0; i < roleArr.length; i++) {
                    roleArr[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(roleArr);
            }
        }
        //  返回代码定义的默认配置
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}