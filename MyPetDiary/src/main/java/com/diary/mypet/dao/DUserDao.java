package com.diary.mypet.dao;

import com.diary.mypet.domain.DUserVO;

// DUser 테이블 SQL 작업하는 메소드 소유 인터페이스

public interface DUserDao {

	// 1. 로그인
	// 사용자가 입력한 id와 pw를 매개 변수로 일치하면 테이블에 정보 리턴
	public DUserVO login(DUserVO vo);

	// 2. 회원 가입

	// 3. 회원 탈퇴

	// 4. 회원 정보 수정
	// - 프로필(이미지 & 닉네임) 수정 작업
	// - 회원 정보(비밀번호) 수정 작업 -> 아이디는 수정 불가

}
