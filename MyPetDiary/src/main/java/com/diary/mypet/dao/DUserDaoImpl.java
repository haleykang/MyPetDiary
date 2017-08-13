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

	// 4. 회원 조회 -> 프로필 조회
	@Override
	public DUserVO selectProfile(String id) {

		return sqlSession.selectOne("duser.selectProfile", id);
	}

	// 5. 프로필 변경
	@Override
	public int updateProfile(DUserVO vo) {

		return sqlSession.update("duser.updateProfile", vo);
	}

	// 6. 회원 탈퇴 
	@Override
	public int deleteUser(String id) {
		
		return sqlSession.delete("duser.deleteUser", id);
	}

}
