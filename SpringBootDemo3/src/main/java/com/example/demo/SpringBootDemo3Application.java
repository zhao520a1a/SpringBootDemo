package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

//禁止springboot自动注入多个数据源；
//@SpringBootApplication( exclude ={
//		DataSourceAutoConfiguration.class,
//		DataSourceTransactionManagerAutoConfiguration.class
//})
@SpringBootApplication
public class SpringBootDemo3Application {

	public static void main(String[] args) {
		final ApplicationContext ctx = SpringApplication.run(SpringBootDemo3Application.class, args);
		////获取配置文件参数：方式三
		Environment environment = ctx.getEnvironment();
		System.out.println(environment.getProperty("mysql.spring.datasource.max-active"));
	}
}
