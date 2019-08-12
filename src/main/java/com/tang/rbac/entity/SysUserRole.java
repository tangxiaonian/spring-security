package com.tang.rbac.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "rbac.sys_user_role")
public class SysUserRole {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "role_id")
    private Integer roleId;
}