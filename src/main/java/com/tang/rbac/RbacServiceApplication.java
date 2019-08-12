package com.tang.rbac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author ASUS
 * @create 2019-08-09 21:16
 */
@MapperScan(basePackages = {"com.tang.rbac.mapper"})
@SpringBootApplication
public class RbacServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(RbacServiceApplication.class, args);

    }

}
