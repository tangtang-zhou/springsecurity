package com.tang.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tang.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Map<String, Object>> getUserPermission(List<String> roles);

}
