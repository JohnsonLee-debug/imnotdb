package com.imnotdb.imnotdb.config;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class NutzDaoConfiguration {
    @Bean
    public NutDao nutDao(DataSource dataSource){
        return new NutDao(dataSource);
    }
}
