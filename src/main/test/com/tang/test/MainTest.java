package com.tang.test;

import com.tang.rbac.RbacServiceApplication;
import com.tang.rbac.entity.SysPermission;
import com.tang.rbac.entity.SysUser;
import com.tang.rbac.mapper.SysPermissionMapper;
import com.tang.rbac.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ASUS
 * @create 2019-08-10 15:11
 */
@SpringBootTest(classes = { RbacServiceApplication.class })
@RunWith(SpringRunner.class)
public class MainTest {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void test1() {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        SysUser user = new SysUser();

        user.setUsername("zhangsan");

        user = userMapper.selectOne(user);

        System.out.println( user );

        user.setPassword( passwordEncoder.encode(user.getPassword()));

        Example example = new Example(SysUser.class);

        example.createCriteria().andEqualTo("username", user.getUsername());

        int update = userMapper.updateByExample(user, example);

        System.out.println( update );
    }
    
    @Test
    public void test(){

//        SysUser sysUser = userMapper.selectByPrimaryKey(1);

        SysUser sysUser = userMapper.selectByUserName("lisi");

        System.out.println(sysUser);

//        List<SysPermission> permissions = permissionMapper.findPermissionByUserName("lisi");
//
//        permissions.forEach(System.out::println);
//
//        System.out.println(permissions.size());

    }

}
