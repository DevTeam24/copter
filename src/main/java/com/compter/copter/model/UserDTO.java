package com.compter.copter.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class UserDTO {

    private Long userId;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String phone;

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 255)
    private String password;

    @NotNull
    private Long userRole;

    @NotNull
    private Long companyUser;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Long getUserRole() {
        return userRole;
    }

    public void setUserRole(final Long userRole) {
        this.userRole = userRole;
    }

    public Long getCompanyUser() {
        return companyUser;
    }

    public void setCompanyUser(final Long companyUser) {
        this.companyUser = companyUser;
    }

}
