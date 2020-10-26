package com.tang.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tang.entity.GroupRole;
import com.tang.service.GroupRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/groupRole")
public class GroupRoleController {

    @Autowired
    GroupRoleService groupRoleService;

    @ApiOperation("给组添加角色")
    @PostMapping("/addGroupRole")
    public String addGroupRole(@RequestBody String groupRoleListJson){
        JSONObject jsonObject = JSON.parseObject(groupRoleListJson);
        JSONArray groupRole= (JSONArray) jsonObject.get("groupRoles");
        List<GroupRole> list = JSONArray.parseArray(groupRole.toJSONString(), GroupRole.class);
        boolean b = groupRoleService.saveBatch(list);
        return String.valueOf(b);
    }

    @ApiOperation("为组删除角色，可以批量删除")
    @DeleteMapping("/removeGroupRole")
    public String removeGroupUser(@RequestBody String groupUserListJson){
        JSONObject jsonObject = JSON.parseObject(groupUserListJson);
        JSONArray userRole= (JSONArray) jsonObject.get("groupRoles");
        List<GroupRole> list = JSONArray.parseArray(userRole.toJSONString(), GroupRole.class);
        Map<String,Object> map = new HashMap<>();
        boolean b = false;
        for (GroupRole groupRole : list) {
            map.put("role_id", groupRole.getRoleId());
            map.put("group_id", groupRole.getGroupId());
            b = groupRoleService.removeByMap(map);
        }
        return String.valueOf(b);
    }

    @ApiOperation("根据组id查看组及其角色")
    @GetMapping("queryGroupRoles")
    public String queryGroupRoles(int groupId) {
        List<Map<String, Object>> mapList = groupRoleService.queryGroupRoles(groupId);
        return mapList.toString();
    }


}
