package com.tang.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tang.entity.JwtUser;
import com.tang.entity.Role;
import com.tang.entity.User;
import com.tang.mapper.RoleMapper;
import com.tang.mapper.UserMapper;
import com.tang.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        StringBuilder roles = new StringBuilder();
        User user = userMapper.queryUserLogin(s);
        List<Map<String, Object>> selectUserRole = userRoleMapper.selectUserRole(user.getId());
        for (Map<String, Object> map: selectUserRole){
            roles.append(map.get("name")).append(",");
        }
        roles.deleteCharAt(roles.length() - 1);
        user.setRole(roles.toString());
        return new JwtUser(user);
    }
}