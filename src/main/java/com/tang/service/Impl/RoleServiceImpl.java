package com.tang.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.entity.Role;
import com.tang.mapper.RoleMapper;
import com.tang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> queryRoleList(String roleName) {
        QueryWrapper<Role> wrapper = null;
        if (roleName != null){
            wrapper = new QueryWrapper<>();
            wrapper.like("name",roleName);
        }
        return roleMapper.selectList(wrapper);
    }
}
