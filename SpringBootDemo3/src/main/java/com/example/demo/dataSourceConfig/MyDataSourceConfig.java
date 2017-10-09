package com.example.demo.dataSourceConfig;


import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/*
 * 练习读取配置文件信息的方式；使用连接池，生成jdbcTemplate；
 * 注意：不能同默认内置的方式一起用，想要想要使用它需要将JdbcConfig的@EnableAutoConfiguration去掉，并且最好在项目入口禁止springboot自动注入多个数据源；
 */
@Slf4j
@Configuration
@Data
public class DataSourceConfig {

	//获取配置文件参数，方式二
    @Value("${mysql.spring.datasource.max-active}")
    private int maxActive;
    @Value("${mysql.spring.datasource.min-idle}")
    private int minIdle;



	@Bean(name = "mysqlDataSourceProperties")
	@ConfigurationProperties(prefix = "mysql.spring.datasource")  //获取配置文件参数，方式一
    DataSourceProperties mysqlDataSourceProperties() {
		return new DataSourceProperties();
	}

	/**
	 * 多数据源配置时，其中一个数据源上必须加@Primary
	 * @return data source
	 */
	@Bean
	@Qualifier("mysqlDataSource")
	@ConfigurationProperties(prefix = "mysql.spring.datasource")
	@Primary
	public DataSource mysqlDataSource() {
		final DataSourceProperties properties = this.mysqlDataSourceProperties();

		//手动注入连接池的参数，因为spring无法自动绑定
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName(properties.getDriverClassName());
		ds.setJdbcUrl(properties.getUrl());
		ds.setUsername(properties.getUsername());
		ds.setPassword(properties.getPassword());
		ds.setMaximumPoolSize(maxActive);
		ds.setMinimumIdle(minIdle);
		ds.setIdleTimeout(60000);
		ds.setConnectionTimeout(1800000);
		ds.setValidationTimeout(3000);
		ds.setMaxLifetime(60000);
		return ds;
	}

	@Bean(name = "mysqlJdbcTemplate")
	public JdbcTemplate mysqlJdbcTemplate() {
		return new JdbcTemplate(this.mysqlDataSource());
	}

}
