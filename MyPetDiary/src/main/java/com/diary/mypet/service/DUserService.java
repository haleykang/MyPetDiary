package com.diary.mypet.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.diary.mypet.domain.DUserVO;

public interface DUserService {

	// 1. 로그인
	public DUserVO login(DUserVO vo);

	// 2. 아이디 중복 체크 - 중복이면 false, 아니면 true
	public boolean idCheck(String id);

	// 3. 회원 가입
	public int join(DUserVO vo);

	// 4. 프로필 조회
	public DUserVO selectProfile(String id);

	// 5. 프로필 변경
	public int updateProfile(MultipartHttpServletRequest request);

	// 6. 회원 탈퇴
	public int deleteUser(String id);

}
