package com.diary.mypet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diary.mypet.dao.DiaryDao;

// DiaryService 메소드 실제 구현 클래스

import com.diary.mypet.domain.DiaryVO;

@Service
public class DiaryServiceImpl implements DiaryService {

	@Autowired
	private DiaryDao dao;

	// 모아보기 메소드 - id를 기준으로 테이블에 있는 데이터를 가져옴
	@Override
	public List<DiaryVO> selectAllDiary(String id) {

		return dao.selectAllDiary(id);
	}

	@Override
	public DiaryVO selectOneDiary(DiaryVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	// 다이어리 작성하는 메소드
	@Override
	public int insertDiary(DiaryVO vo) {
		// 1) 입력에 필요한 정보 : #{title},#{content},#{id} --> 일단 이미지 입력 생략
		// 아이디는 일단 그냥 입력 - 나중에 회원 테이블 생성 후 변경
		// 아이디 저장
		String id = "root";
		vo.setId(id);
		// 2) 리턴
		return dao.insertDiary(vo);
	}

	@Override
	public int updateDiary(DiaryVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDiary(DiaryVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
