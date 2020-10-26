package com.tang.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.entity.RolePermission;
import com.tang.mapper.RolePermissionMapper;
import com.tang.model.RolePermissionList;
import com.tang.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<RolePermissionList> rolePermissionList() {
        return rolePermissionMapper.rolePermissionList();
    }
}
