package com.tang.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "public.group_role")
public class GroupRole {
    @TableId(type = IdType.AUTO)
    private int id;

    private int groupId;

    private int roleId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
