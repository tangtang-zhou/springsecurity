package com.tang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tang.entity.RolePermission;
import com.tang.model.ResultBody;
import com.tang.service.RolePermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/RolePermission")
public class RolePermissionController {

    @Autowired
    RolePermissionService rolePermissionService;

    @ApiOperation("添加角色所拥有的权限")
    @PostMapping("/addRolePermission")
    public ResultBody addRolePermission(@RequestBody String RolePermissionJson){
        JSONObject jsonObject = JSON.parseObject(RolePermissionJson);
        JSONArray userRole= (JSONArray) jsonObject.get("rolePermission");
        List<RolePermission> list = JSONArray.parseArray(userRole.toJSONString(), RolePermission.class);
        boolean b = rolePermissionService.saveBatch(list);
        return ResultBody.success(b);
    }

    @ApiOperation("删除角色所拥有的权限")
    @DeleteMapping("/removeRolePermission")
    public ResultBody queryRole(@RequestBody String RolePermissionJson) {
        JSONObject jsonObject = JSON.parseObject(RolePermissionJson);
        JSONArray userRole = (JSONArray) jsonObject.get("rolePermission");
        List<RolePermission> list = JSONArray.parseArray(userRole.toJSONString(), RolePermission.class);

        Map<String,Object> map = new HashMap<>();
        boolean b = false;
        for (RolePermission rolePermission : list) {
            map.put("permission_id", rolePermission.getPermissionId());
            map.put("role_id", rolePermission.getRoleId());
            b = rolePermissionService.removeByMap(map);
        }
        return ResultBody.success(b);
    }

}
