package com.tang.rbac.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "rbac.sys_permission")
public class SysPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "per_Name")
    private String perName;

    @Column(name = "per_Tag")
    private String perTag;

}