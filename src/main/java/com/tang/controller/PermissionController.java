package com.tang.controller;

import com.tang.entity.Permission;
import com.tang.model.ResultBody;
import com.tang.service.PermissionService;
import com.tang.util.JwtTokenUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @ApiOperation("展示所有权限,column为数据库列名（name和p_desc两个值），desc为模糊查询内容")
    @GetMapping("/permissionList")
    public ResultBody showPermissionList(String column, String desc){
        List<Permission> permissions = permissionService.showPermissionList(column, desc);
        return ResultBody.success(permissions);
    }

    @ApiOperation("添加权限")
    @PostMapping("/addPermission")
    public ResultBody addPermission(Permission permission){
        int i = permissionService.addPermission(permission);
        return ResultBody.success(i);
    }

    @ApiOperation("更新权限")
    @PutMapping("/updatePermission")
    public ResultBody updatePermission(Permission permission){
        int i = permissionService.updatePermission(permission);
        return ResultBody.success(i);
    }
    @ApiOperation("获取权限菜单")
    @GetMapping("/getUserPermission")
    public ResultBody getUserPermission(HttpServletRequest request){
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        List<String> roles = Arrays.asList(JwtTokenUtils.getUserRole(token).split(","));
        List<Map<String, Object>> userPermission = permissionService.getUserPermission(roles);
        return ResultBody.success(userPermission);
    }

}
