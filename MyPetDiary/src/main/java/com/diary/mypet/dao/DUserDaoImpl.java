package com.diary.mypet.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diary.mypet.domain.DUserVO;

@Repository
public class DUserDaoImpl implements DUserDao {

	@Autowired
	private SqlSession sqlSession;

	// 1. 로그인 메소드
	@Override
	public DUserVO login(DUserVO vo) {

		return sqlSession.selectOne("duser.login", vo);
	}

}
