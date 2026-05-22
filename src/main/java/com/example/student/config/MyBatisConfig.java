package com.example.student.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.student.mapper")
public class MyBatisConfig {
}
