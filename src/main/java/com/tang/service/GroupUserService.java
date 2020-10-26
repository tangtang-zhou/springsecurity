package com.tang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.entity.GroupUser;

import java.util.List;
import java.util.Map;

public interface GroupUserService extends IService<GroupUser> {

    List<Map<String, Object>> queryGroupUsers(int groupId);

}
