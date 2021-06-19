package com.imnotdb.imnotdb.config;

import com.imnotdb.imnotdb.utils.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SnowFlakeConfig {
    @Bean
    @Primary
    public SnowFlake snowFlake(){
        return new SnowFlake(1, 1);
    }
}
