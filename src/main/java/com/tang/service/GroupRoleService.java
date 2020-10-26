package com.tang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.entity.GroupRole;

import java.util.List;
import java.util.Map;

public interface GroupRoleService extends IService<GroupRole> {

    List<Map<String, Object>> queryGroupRoles(int groupId);

}
