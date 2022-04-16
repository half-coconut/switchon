package com.coconut.ds20;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.coconut.ds20.mapper")
public class DS20Application {

    public static void main(String[] args) {
        SpringApplication.run(DS20Application.class, args);
    }
}
