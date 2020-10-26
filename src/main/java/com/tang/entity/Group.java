package com.tang.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "public.g_group")
public class Group {

    @TableId(type = IdType.AUTO)
    private int id;

    private String name;

    private String description;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
