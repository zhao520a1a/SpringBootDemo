package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootDemo3ApplicationTests {


	//@Autowired
	//@Qualifier("defaultJdbcTemplate")
	//private JdbcTemplate defaultJdbcTemplate;


	@Autowired
	@Qualifier("myJdbcTemplate")
	private JdbcTemplate mysqlJdbcTemplate;




	/*测试数据库连接*/
	@Test
	public void testDefaultDB() {
		try {
			String sql = "SELECT count(1) from tb_item;";
			Integer t = mysqlJdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
				@Override
				public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
					if(rs.next()){
						log.info("{}",rs.getInt(1));
					}
					return rs.getInt(1);
				}
			});
			log.info("测试值：{}", t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
