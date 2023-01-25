package com.compter.copter.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class RoleDTO {

    private Long roleId;

    @NotNull
    @Size(max = 255)
    private String name;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(final Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
