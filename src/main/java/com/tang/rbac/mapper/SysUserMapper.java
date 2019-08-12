package com.tang.rbac.mapper;

import com.tang.rbac.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

public interface SysUserMapper extends MyMapper<SysUser> {

    public SysUser selectByUserName(@Param("username") String username);

}