package com.tang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tang.entity.UserRole;
import com.tang.model.ResultBody;
import com.tang.service.UserRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;


    @ApiOperation("设置用户角色,传入role对象数组")
    /*
    * {
    "userRole":[
        {
            "userId": 1,
            "roleId": 1
        },{
            "userId": 2,
            "roleId": 2
        }
    ]
}
    *
    * */
    @PostMapping("/setRole")
    public ResultBody setRole(@RequestBody String json){
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray userRole= (JSONArray) jsonObject.get("userRole");
        List<UserRole> list = JSONArray.parseArray(userRole.toJSONString(), UserRole.class);
        boolean b = userRoleService.saveBatch(list);
        return ResultBody.success(b);
    }

    @ApiOperation("删除用户角色")
    @DeleteMapping("/removeRole")
    public ResultBody removeRole(@RequestBody String json){
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray userRole= (JSONArray) jsonObject.get("userRole");
        List<UserRole> list = JSONArray.parseArray(userRole.toJSONString(), UserRole.class);
        Map<String,Object> map = new HashMap<>();
        boolean b = false;
        for (UserRole role : list) {
            map.put("user_id", role.getUserId());
            map.put("role_id", role.getRoleId());
            b = userRoleService.removeByMap(map);
        }
        return ResultBody.success(b);
    }


}
