package com.tang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tang.entity.GroupUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GroupUserMapper extends BaseMapper<GroupUser> {

    List<Map<String, Object>> queryGroupUsers(int groupId);

}
