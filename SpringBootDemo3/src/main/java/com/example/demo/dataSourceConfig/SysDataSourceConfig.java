package com.example.demo.dataSourceConfig;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/*
 * 默认使用SpringBoot中内部的spring.datasource属性注入，使用 生成jdbcTemplate；
 */
@Slf4j
@Configuration
@EnableAutoConfiguration  //必须要有,才能注入成功
public class JdbcConfig {
	@Bean(name="defaultJdbcTemplate")
	@Primary
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}

}
