package com.example.demo.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName: DBConnection
 * @Description: JDBC连接工具类
 * @author chenjianfeng
 * @date 2015年7月31日 下午4:07:30
 * 
 * @version 1.0.0
 */
public class DBConnection {

	// 关闭有结果集数据库
	public static void close(ResultSet rs, Statement stat, Connection conn) throws Exception {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("关闭连接数据库资源异常");
		}
	}

	// 关闭没有结果集
	public static void close(Statement stat, Connection conn) throws Exception {
		try {
			if (stat != null) {
				stat.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("关闭连接数据库资源异常");
		}
	}

	public static void main(String[] args) throws Exception {
	}
}
