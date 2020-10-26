package com.tang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.entity.RolePermission;
import com.tang.model.RolePermissionList;

import java.util.List;
import java.util.Map;

public interface RolePermissionService extends IService<RolePermission> {

    List<RolePermissionList> rolePermissionList();

}
