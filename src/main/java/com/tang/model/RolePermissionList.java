package com.tang.model;

import com.tang.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RolePermissionList implements Serializable {

    private String permission;
    private List<Role> roles;

}
