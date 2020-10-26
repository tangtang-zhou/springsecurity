package com.tang.entity;


import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.util.Date;

@Data
@TableName(value = "public.u_user")
@ApiModel("用户实体类")
public class User {

    @TableId(type = IdType.AUTO)
    private int id;

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @TableField(exist = false)
    private String role;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField("is_deleted")
    @TableLogic
    private int deleted;

}
