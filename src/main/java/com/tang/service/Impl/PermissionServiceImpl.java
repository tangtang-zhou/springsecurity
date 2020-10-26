package com.tang.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tang.entity.Permission;
import com.tang.mapper.PermissionMapper;
import com.tang.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> showPermissionList(String column, String desc) {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();

        if (column != null && desc != null) {
            wrapper.like(column, desc);
        } else {
            wrapper = null;
        }
        return permissionMapper.selectList(wrapper);
    }

    @Override
    public int addPermission(Permission permission) {
        return permissionMapper.insert(permission);
    }

    @Override
    public int updatePermission(Permission permission) {

        return permissionMapper.updateById(permission);
    }

    @Override
    public List<Map<String, Object>> getUserPermission(List<String> roles) {
        return permissionMapper.getUserPermission(roles);
    }
}
