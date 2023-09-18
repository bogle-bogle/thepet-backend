package com.thehyundai.thepet.global.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "primaryDataSource")
    @Qualifier("primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
