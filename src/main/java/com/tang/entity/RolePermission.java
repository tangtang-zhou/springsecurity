package com.tang.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "public.role_permission")
public class RolePermission {

    @TableId(type = IdType.AUTO)
    private int id;

    private int roleId;

    private int permissionId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
