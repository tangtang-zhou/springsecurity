package com.tang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tang.entity.RolePermission;
import com.tang.model.RolePermissionList;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    List<RolePermissionList> rolePermissionList();

}
