package com.learn.mybatisplusstage01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.learn.mybatisplusstage01.mapper")
public class MybatisPlusStage01Application {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusStage01Application.class, args);
    }

}
