package com.diary.mypet;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.ibatis.session.SqlSession;
import org.junit.*;

// 테스트 클래스
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class DiaryTest {

	@Inject
	private DataSource ds;

	@Test
	public void connectionTest() throws Exception {
		Connection con = null;

		try {

			con = ds.getConnection();
			System.out.println(con);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// SqlSession 연결 테스트
	@Inject
	private SqlSession sqlSession;

	@Test
	public void testSql() throws Exception {
		try {
			System.out.println(sqlSession);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
