package com.example.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class TimescaleConfig {

    @Bean(name="timescaleDataSource")
    @ConfigurationProperties(prefix = "spring.timescale-datasource")
    public DataSource timescaleDataSource() {
        return DataSourceBuilder.create().build();
    }
}
