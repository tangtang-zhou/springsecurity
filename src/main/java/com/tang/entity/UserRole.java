package com.tang.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "public.user_role")
public class UserRole {

    @TableId(type = IdType.AUTO)
    private int id;

    private int userId;

    private int roleId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
