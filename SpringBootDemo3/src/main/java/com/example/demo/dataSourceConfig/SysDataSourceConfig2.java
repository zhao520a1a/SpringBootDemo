package com.example.demo.dataSourceConfig;


/*
 * 默认使用Tomcat.jdbc.pool连接池，（如果不排除它的jar，该连接池在Springboot中优先级最高,不管你是否设置了其他连接池）
 */
//@Configuration
//@EnableAutoConfiguration  //必须要有,才能注入成功
//public class SysDataSourceConfig2 {
//
//	@Bean
//	@Primary
//	@ConfigurationProperties(prefix="spring.datasource")
//	public DataSource primaryDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//
//	@Bean
//	@Primary
//	public JdbcTemplate jdbcTemplate(DataSource dataSource){
//		return new JdbcTemplate(dataSource);
//	}
//
//	@Bean(name = "secondDs")
//	@ConfigurationProperties(prefix = "spring.second-datasource")
//	public DataSource secondDs() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean(name = "secondJdbcTemplate")
//	public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDs") DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}
//
//
//}
