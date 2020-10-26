package com.tang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tang.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {

    User queryUserLogin(String username);

    List<User> queryUserList(String username);

}
