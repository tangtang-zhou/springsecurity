package com.tang.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.entity.GroupUser;
import com.tang.mapper.GroupUserMapper;
import com.tang.service.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GroupUserServiceImpl extends ServiceImpl<GroupUserMapper, GroupUser> implements GroupUserService {

    @Autowired
    GroupUserMapper groupUserMapper;

    @Override
    public List<Map<String, Object>> queryGroupUsers(int groupId) {

        return groupUserMapper.queryGroupUsers(groupId);

    }
}
