package com.example.demo.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;


@ConfigurationProperties(prefix=ExecutorProperties.PREFIX)
@Data
public class ExecutorProperties {
	public static final String PREFIX = "executor";
	/**
	 * sql执行成功后返回结果集的最多记录数
	 */
	private int responseSize;
}
