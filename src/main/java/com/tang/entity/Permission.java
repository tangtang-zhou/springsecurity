package com.tang.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "public.permission")
public class Permission {

    @TableId(type = IdType.AUTO)
    private int id;

    @TableField("f_url")
    private String name;

    @TableField(value = "f_menu_name")
    private String desc;

    @TableField("f_menu_pid")
    private int parentId;

    @TableField("f_is_leaf")
    private int leaf;

    @TableField("f_sort")
    private int sort;

    @TableField("f_level")
    private int level;

    @TableField("f_status")
    private int status;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
