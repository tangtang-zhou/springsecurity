package com.tang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tang.entity.Role;
import com.tang.model.ResultBody;
import com.tang.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @ApiOperation("查询角色信息")
    @GetMapping("/queryRole")
    public ResultBody queryRole(int roleId) {
        Role byId = roleService.getById(roleId);
        return ResultBody.success(byId);
    }

    @ApiOperation("获取所有角色")
    @GetMapping("/roleList")
    public ResultBody showRoleList(String roleName) {
        List<Role> roleList = roleService.queryRoleList(roleName);

        return ResultBody.success(roleList);
    }

    @ApiOperation("添加角色")
    @PostMapping("/addRole")
    public ResultBody addRole(Role role) {
        boolean save = roleService.save(role);
        return ResultBody.success(save);
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/removeRole")
    public ResultBody removeRole(@RequestBody String roleIdListJson) {
        List<Integer> idList = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(roleIdListJson);
        JSONArray groupIdList = (JSONArray) jsonObject.get("groupIdList");
        for(int i=0;i<groupIdList.size();i++) {
            JSONObject job = groupIdList.getJSONObject(i);   // 遍历 jsonarray 数组，把每一个对象转成 json 对象
            idList.add(Integer.parseInt(job.get("id").toString()));   // 得到 每个对象中的属性值
        }
        boolean b = roleService.removeByIds(idList);
        return ResultBody.success(b);
    }



    @ApiOperation("更新角色")
    @PutMapping("/updateRole")
    public ResultBody updateRole(Role role) {
        boolean b = roleService.updateById(role);
        return ResultBody.success(b);
    }


}
