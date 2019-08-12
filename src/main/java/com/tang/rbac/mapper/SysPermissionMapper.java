package com.tang.rbac.mapper;

import com.tang.rbac.entity.SysPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface SysPermissionMapper extends MyMapper<SysPermission> {

    public List<SysPermission> findPermissionByUserName(@Param("username") String username);

}