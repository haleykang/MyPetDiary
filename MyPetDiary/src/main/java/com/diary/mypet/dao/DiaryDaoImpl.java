package com.diary.mypet.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diary.mypet.domain.DiaryVO;

// DiaryDao를 상속 받는 클래스 - 실제 SQL 작업 메소드 구현
@Repository
public class DiaryDaoImpl implements DiaryDao {

	@Autowired
	private SqlSession sqlSession;

	// 1. 모아보기
	@Override
	public List<DiaryVO> selectAllDiary(String id) {

		return sqlSession.selectList("diary.selectAllDiary", id);
	}

	// 2. 상세보기
	@Override
	public DiaryVO selectOneDiary(DiaryVO vo) {

		return sqlSession.selectOne("diary.selectOneDiary", vo);
	}

	// 3. 조회수 증가
	@Override
	public int updateCount(DiaryVO vo) {

		return sqlSession.update("diary.updateCount", vo);
	}

	// 4. 다이어리 등록
	@Override
	public int insertDiary(DiaryVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("diary.insertDiary", vo);
	}

	// 5. 다이어리 수정
	@Override
	public int updateDiary(DiaryVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("diary.updateDiary", vo);
	}

	// 6. 다이어리 삭제
	@Override
	public int deleteDiary(DiaryVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.delete("diary.deleteDiary", vo);
	}

	// 7. 모두의 다이어리
	@Override
	public List<DiaryVO> shareAllDiary() {

		return sqlSession.selectList("diary.shareAllDiary");
	}

}