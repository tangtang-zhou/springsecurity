package com.tang.controller;

import com.tang.entity.User;
import com.tang.mapper.UserRoleMapper;
import com.tang.model.ResultBody;
import com.tang.service.UserService;
import com.tang.util.ElasticsearchUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ElasticsearchUtils elasticsearchUtils;

    @ApiOperation("查询用户信息")
    @GetMapping("/queryUser")
    public ResultBody queryUser(String username) {
        User user = userService.findUserByName(username);
        StringBuilder roles = new StringBuilder();
        List<Map<String, Object>> mapList = userRoleMapper.selectUserRole(user.getId());
        for (Map<String, Object> map: mapList){
            roles.append(map.get("name")).append(",");
        }
        roles.deleteCharAt(roles.length() - 1);
        user.setRole(roles.toString());
        return ResultBody.success(user);
    }

    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    @ApiImplicitParams({
            @ApiImplicitParam()
    })
    public ResultBody addUser(User user) {
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);
        int i = userService.addUser(user);
        return ResultBody.success(i);
    }

    @ApiOperation("查询用户列表,可通过用户名模糊查询")
    @GetMapping("/userList")
    public ResultBody userList(String username) {
        List<User> users = userService.showUserList(username);
        return ResultBody.success(users);
    }

    @ApiOperation("更新用户信息")
    @PutMapping("/updateUser")
    public ResultBody updateUser(User user){
        System.out.println(user);
        boolean b = userService.updateById(user);
        return ResultBody.success(b);
    }

    @ApiOperation("获取用户热点数据")
    @GetMapping("/getUserHotData")
    public ResultBody userHotData() throws IOException, InterruptedException {
        // 防止数据不一致，还未更新数据成功就取出数据
        Thread.sleep(1000);

        List<Map<String, Object>> user_hot = elasticsearchUtils.getDocuments("user_hot");

        return ResultBody.success(user_hot);
    }
}
