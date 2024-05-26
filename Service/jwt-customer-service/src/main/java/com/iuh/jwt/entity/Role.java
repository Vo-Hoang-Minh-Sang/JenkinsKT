package com.iuh.jwt.entity;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    private String roleName;
    private String roleDesciption;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesciption() {
        return roleDesciption;
    }

    public void setRoleDesciption(String roleDesciption) {
        this.roleDesciption = roleDesciption;
    }
}
