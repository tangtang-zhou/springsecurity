package com.tang.service;

import com.tang.entity.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    List<Permission> showPermissionList(String column, String desc);

    int addPermission(Permission permission);

    int updatePermission(Permission permission);

    List<Map<String, Object>> getUserPermission(List<String> roles);

}
