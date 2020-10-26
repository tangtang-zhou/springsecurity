package com.tang.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.entity.GroupRole;
import com.tang.mapper.GroupRoleMapper;
import com.tang.service.GroupRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GroupRoleServiceImpl extends ServiceImpl<GroupRoleMapper, GroupRole> implements GroupRoleService {

    @Autowired
    GroupRoleMapper groupRoleMapper;

    @Override
    public List<Map<String, Object>> queryGroupRoles(int groupId) {
        return groupRoleMapper.queryGroupRoles(groupId);
    }
}
