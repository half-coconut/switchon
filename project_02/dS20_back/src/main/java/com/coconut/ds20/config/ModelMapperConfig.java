package com.coconut.ds20.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/2/6 22:38
 * File: ModelMapperConfig
 * Project: dS9
 */

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        //设置映射时匹配策略
        //Standard表示标准匹配策略，为默认选项
        //Loose表示松散匹配策略
        //Strict表示严格匹配策略
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
