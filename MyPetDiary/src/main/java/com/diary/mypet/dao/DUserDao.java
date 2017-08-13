package com.diary.mypet.dao;

import com.diary.mypet.domain.DUserVO;

// DUser 테이블 SQL 작업하는 메소드 소유 인터페이스

public interface DUserDao {

	// 1. 로그인
	// 사용자가 입력한 id와 pw를 매개 변수로 일치하면 테이블에 정보 리턴
	public DUserVO login(DUserVO vo);

	// 2. 회원 가입
	public int join(DUserVO vo);

	// 2-2. 아이디 중복체크 - 아이디를 입력해서 있으면 아이디를 리턴
	public String idCheck(String id);

	// 3. 회원 탈퇴
	public int deleteUser(String id);

	// 4. 회원 정보 수정
	// - 프로필(이미지 & 닉네임) 수정 작업
	public int updateProfile(DUserVO vo);

	// - 회원 정보(비밀번호) 수정 작업 -> 아이디는 수정 불가

	// 5. 회원 정보 조회
	// 아이디를 기준으로 회원 정보 가져오
	public DUserVO selectProfile(String id);

}