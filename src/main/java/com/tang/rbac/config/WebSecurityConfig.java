package com.tang.rbac.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

/**
 * @author ASUS
 * @create 2019-08-09 22:07
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ImageCodeAuthenticatonFilter imageCodeAuthenticatonFilter;

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 持久化token 到数据库  remember me 功能
     * @param dataSource
     * @return
     */
    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository(DataSource dataSource) {

        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//      配置数据源
        jdbcTokenRepository.setDataSource(dataSource);
//      开启启动时自动创建表功能  第一次运行创建
//        jdbcTokenRepository.setCreateTableOnStartup(true);

        return jdbcTokenRepository;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         测试用的内存数据
//        auth.inMemoryAuthentication()
//                .withUser("user").password(new BCryptPasswordEncoder().encode("123456"))
//                .roles("user")
//                .and()
//                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456"))
//                .roles("admin").authorities("");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//      关闭csrf
        http.csrf().disable();
//      处理权限不足的异常
        http.exceptionHandling()
                .accessDeniedHandler(new MyAccessDeniedeHandler());
//      定义  权限验证规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()   // 基于权限验证
                .antMatchers("/imageCode").permitAll()   // 基于权限验证
                .antMatchers("/addProduct").hasAnyAuthority("add_permission")
                .antMatchers("/deleteProduct").hasAnyAuthority("delete_permission")
                .antMatchers("/edit_permission").hasAnyAuthority("edit_permission")
                .antMatchers("/updateProduct").hasAnyAuthority("update_permission")
                .anyRequest() // 其他请求都要有权限才能访问
                .authenticated();

//    # 自定义过滤器配置  进行验证码对比
        http.addFilterBefore(imageCodeAuthenticatonFilter,
                UsernamePasswordAuthenticationFilter.class);

//      jdbc验证
        http.userDetailsService(userDetailsService);

//      表单登录
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
//                .successForwardUrl("/loginSucc")  # 会出现表单重复提交
                .successHandler(new MyAuthenticationSuccessHandler()) //自定义登录成功请求，重定向到登陆成功页面，消除转发引起的表单重复提交
                .permitAll()
        ;

//        remember me 功能
        http.rememberMe()
                .tokenValiditySeconds(3600)
                // 将token 存储在数据库中
                .tokenRepository(jdbcTokenRepository(dataSource))
                //必须设置userDetailsService  需要查询数据库对比密码
                .userDetailsService(userDetailsService);
    }
}
