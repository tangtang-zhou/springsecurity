package com.tang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tang.entity.GroupRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GroupRoleMapper extends BaseMapper<GroupRole> {

    List<Map<String, Object>> queryGroupRoles(int groupId);

}
