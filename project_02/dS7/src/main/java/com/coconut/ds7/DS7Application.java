package com.coconut.ds7;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.coconut.ds7.mapper")
@EnableCaching
public class DS7Application {

    public static void main(String[] args) {
        SpringApplication.run(DS7Application.class, args);
    }

}
