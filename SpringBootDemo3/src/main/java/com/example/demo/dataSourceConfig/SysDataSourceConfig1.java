package com.example.demo.dataSourceConfig;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/*
 *
 * 使用hikari连接池, 配置文件要使用hikari的参数命名规则
  *
 */
@Configuration
public class SysDataSourceConfig1 {

    @Bean(name = "test1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test1")
    @Primary
    public DataSource test1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }


    @Bean(name = "test2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test2")
    public DataSource test2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "test2JdbcTemplate")
    public JdbcTemplate secondJdbcTemplate(@Qualifier("test2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
