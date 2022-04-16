package com.coconut.ds7.config;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/12 17:23
 * File: ModelMapperConfig
 * Project: dS7
 */

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 对象映射工具 Bean
 */
@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
