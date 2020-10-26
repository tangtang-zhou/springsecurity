package com.tang.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.entity.User;
import com.tang.mapper.UserMapper;
import com.tang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserByName(String username) {
        return userMapper.queryUserLogin(username);
    }

    @Override
    public List<User> showUserList(String username) {
        return userMapper.queryUserList(username);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }


}
