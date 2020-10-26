package com.tang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tang.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<Map<String, Object>> selectUserRole(int userId);

}
