package com.diary.mypet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diary.mypet.dao.DUserDao;
import com.diary.mypet.domain.DUserVO;

@Service
public class DUserServiceImpl implements DUserService {

	@Autowired
	private DUserDao dao;

	// 1. 로그인
	@Override
	public DUserVO login(DUserVO vo) {

		return dao.login(vo);
	}

	// 2. 아이디 중복 체크
	@Override
	public boolean idCheck(String id) {
		// 1) 아이디 입력해서 리턴값 저장
		String result = dao.idCheck(id);
		// 2) 중복 여부 확인
		if (result == null) {
			// return == null 이면 중복되는 아이디 없
			return true;
		}

		// result 가 null이 아니면 false
		return false;
	}

	// 3. 회원 가입
	@Override
	public int join(DUserVO vo) {

		return dao.join(vo);
	}

}
