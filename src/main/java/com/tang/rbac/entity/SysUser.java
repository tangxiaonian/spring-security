package com.tang.rbac.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Table(name = "rbac.sys_user")
public class SysUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @Column(name = "createTime")
    private Date createtime;

    @Column(name = "lastLoginDate")
    private Date lastlogindate;

    @Column(name = "accountNonExpired")
    private boolean isAccountNonExpired;

    @Column(name = "accountNonLocked")
    private boolean isAccountNonLocked;

    private Collection<? extends GrantedAuthority> authorities;

    @Column(name = "credentialsNonExpired")
    private boolean isCredentialsNonExpired;

    @Column(name="enabled")
    private boolean enabled;

}