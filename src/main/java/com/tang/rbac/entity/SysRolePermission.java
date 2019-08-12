package com.tang.rbac.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "rbac.sys_role_permission")
public class SysRolePermission {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Id
    @Column(name = "perm_id")
    private Integer permId;

}