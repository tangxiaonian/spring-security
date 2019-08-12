package com.tang.rbac.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "rbac.sys_role")
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_desc")
    private String roleDesc;

}