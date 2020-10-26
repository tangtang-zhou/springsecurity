package com.tang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends IService<User> {

    User findUserByName(String username);

    List<User> showUserList(String username);

    int addUser(User user);

}
