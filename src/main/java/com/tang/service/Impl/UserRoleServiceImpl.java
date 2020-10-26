package com.tang.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.entity.UserRole;
import com.tang.mapper.UserRoleMapper;
import com.tang.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {


}
