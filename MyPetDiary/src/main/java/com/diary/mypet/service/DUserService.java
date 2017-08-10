package com.diary.mypet.service;

import com.diary.mypet.domain.DUserVO;

public interface DUserService {

	// 1. 로그인
	public DUserVO login(DUserVO vo);

	// 2. 아이디 중복 체크 - 중복이면 false, 아니면 true
	public boolean idCheck(String id);

	// 3. 회원 가입
	public int join(DUserVO vo);

}
