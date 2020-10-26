package com.tang.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "public.group_user")
public class GroupUser {

    @TableId(type = IdType.AUTO)
    private int id;

    private int groupId;

    private int userId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
