package com.tang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.entity.Role;

import java.util.List;


public interface RoleService extends IService<Role> {

    List<Role> queryRoleList(String roleName);

}
