package com.tang.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tang.entity.GroupUser;
import com.tang.service.GroupUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/groupUser")
public class GroupUserController {

    @Autowired
    GroupUserService groupUserService;

    @ApiOperation("为组添加用户，可以批量添加")
    @PostMapping("/addGroupUser")
    public String addGroupUser(@RequestBody String groupUserListJson){
        JSONObject jsonObject = JSON.parseObject(groupUserListJson);
        JSONArray groupUser= (JSONArray) jsonObject.get("userIdGroupId");
        List<GroupUser> list = JSONArray.parseArray(groupUser.toJSONString(), GroupUser.class);
        boolean b = groupUserService.saveBatch(list);
        return String.valueOf(b);
    }

    @ApiOperation("为组删除用户，可以批量删除")
    @DeleteMapping("/removeGroupUser")
    public String removeGroupUser(@RequestBody String groupUserListJson){
        JSONObject jsonObject = JSON.parseObject(groupUserListJson);
        JSONArray userRole= (JSONArray) jsonObject.get("userIdGroupId");
        List<GroupUser> list = JSONArray.parseArray(userRole.toJSONString(), GroupUser.class);
        Map<String,Object> map = new HashMap<>();
        boolean b = false;
        for (GroupUser groupUser : list) {
            map.put("user_id", groupUser.getUserId());
            map.put("group_id", groupUser.getGroupId());
            b = groupUserService.removeByMap(map);
        }
        return String.valueOf(b);
    }

    @ApiOperation("根据组id查看组及其成员")
    @GetMapping("queryGroupUsers")
    public String queryGroupUsers(int groupId) {
        List<Map<String, Object>> mapList = groupUserService.queryGroupUsers(groupId);
        return mapList.toString();
    }

}
