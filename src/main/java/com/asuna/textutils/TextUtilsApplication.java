package com.asuna.textutils;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.asuna.textutils.dao")
@SpringBootApplication
public class TextUtilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextUtilsApplication.class, args);
    }

}
