package com.diary.mypet.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.diary.mypet.domain.DiaryVO;

// 다이어리 관련 작업을 위한 메소드 소유 인터페이스
public interface DiaryService {

	// 1. 모아보기(테이블에 있는 데이터를 아이디 기준으로 가져와서 보여주기, 글 번호 내림차순)
	public List<DiaryVO> selectAllDiary(String id);

	// 2. 상세보기(특정 아이디 & 선택한 글번호를 기준으로 데이터를 가져와서 보여주기)
	public DiaryVO selectOneDiary(DiaryVO vo);

	// 3. 일기 작성(글번호(자동), 제목, 내용, 이미지, 아이디(로그인하면 자동으로 등록) 입력 받아서 테이블에 저장
	public int insertDiary(MultipartHttpServletRequest request);

	// 4. 일기 수정(제목, 내용, 이미지) 입력 받아서 테이블에 저장
	// 매개 변수 MultipartHttpServletRequest로 변경
	public int updateDiary(MultipartHttpServletRequest request);

	// 4-1. 수정 페이지로 이동
	public DiaryVO goUpdate(DiaryVO vo);

	// 5. 일기 삭제(글 번호, 아이디 기준으로 삭제)
	public int deleteDiary(DiaryVO vo);
	
	// 6. 모두의 일기장 
	public List<DiaryVO> shareAllDiary();

}