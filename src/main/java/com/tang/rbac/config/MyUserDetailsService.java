package com.tang.rbac.config;

import com.tang.rbac.entity.SysPermission;
import com.tang.rbac.entity.SysUser;
import com.tang.rbac.mapper.SysPermissionMapper;
import com.tang.rbac.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义处理 用户登录的逻辑
 * @author ASUS
 * @create 2019-08-10 18:57
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名查询用户
        SysUser user = userMapper.selectByUserName(username);

        if (user.getId() != null) {
//        todo 密码加密
            user.setPassword(user.getPassword());

            //用户存在  取出用户的权限列表
            List<SysPermission> listPerm = permissionMapper.findPermissionByUserName(username);

            listPerm.forEach(System.out::println);

            if (listPerm.size() > 0) {

                List<SimpleGrantedAuthority> list = new ArrayList<>();
                //封装用户权限集合
                for (SysPermission permission : listPerm) {

                    list.add(new SimpleGrantedAuthority(permission.getPerTag()));
                }
                // 设置权限集合
                user.setAuthorities(list);
            }
            return user;
        }
        return new User("", "", new ArrayList<SimpleGrantedAuthority>());
    }
}
