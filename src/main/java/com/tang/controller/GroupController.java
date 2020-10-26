package com.tang.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tang.entity.Group;
import com.tang.model.ResultBody;
import com.tang.service.GroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @ApiOperation("添加组")
    @PostMapping("/addGroup")
    public ResultBody addGroup(Group group){
        boolean save = groupService.save(group);
        return ResultBody.success(save);
    }

    @ApiOperation("删除组")
    @DeleteMapping("/removeGroup")
    public ResultBody removeGroup(@RequestBody String groupIdListJson){
        List<Integer> idList = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(groupIdListJson);
        JSONArray groupIdList = (JSONArray) jsonObject.get("groupIdList");
        for(int i=0;i<groupIdList.size();i++) {
            JSONObject job = groupIdList.getJSONObject(i);   // 遍历 jsonarray 数组，把每一个对象转成 json 对象
            idList.add(Integer.parseInt(job.get("id").toString()));   // 得到 每个对象中的属性值
        }
        boolean b = groupService.removeByIds(idList);
        return ResultBody.success(b);
    }

    @ApiOperation("修改组")
    @PutMapping("/updateGroup")
    public ResultBody updateGroup(Group group){
        System.out.println(group);
        boolean save = groupService.updateById(group);
        return ResultBody.success(save);
    }

    @ApiOperation("查看所有组信息,可传递组名进行模糊查询")
    @GetMapping("/queryGroup")
    public ResultBody queryGroup(String groupNameOrLikeName){
        System.out.println(groupNameOrLikeName);
        QueryWrapper<Group> wrapper = null;
        if (groupNameOrLikeName != null){
            wrapper = new QueryWrapper<>();
            wrapper.like("name", groupNameOrLikeName);
        }

        Map<String, Object> save = groupService.getMap(wrapper);
        return ResultBody.success(save);
    }

}
