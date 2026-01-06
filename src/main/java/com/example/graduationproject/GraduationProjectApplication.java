package com.example.graduationproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class })
@MapperScan(basePackages = {"com.example.graduationproject.mapper"})
public class GraduationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationProjectApplication.class, args);
    }

}
