package com.diary.mypet.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diary.mypet.domain.DUserVO;

@Repository
public class DUserDaoImpl implements DUserDao {

	@Autowired
	private SqlSession sqlSession;

	// 1. 로그인
	@Override
	public DUserVO login(DUserVO vo) {

		return sqlSession.selectOne("duser.login", vo);
	}

	// 2. 아이디 중복체크
	@Override
	public String idCheck(String id) {

		return sqlSession.selectOne("duser.idCheck", id);
	}

	// 3. 회원 가입
	@Override
	public int join(DUserVO vo) {

		return sqlSession.insert("duser.join", vo);
	}

}
