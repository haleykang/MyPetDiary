package com.diary.mypet.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

	// 상세보기 메소드 - id와 글 번호를 기준으로 테이블의 데이터를 가져옴
	// 그리고 조회수 1 증가
	@Override
	public DiaryVO selectOneDiary(DiaryVO vo) {
		// 1) 조회수 1 증가
		dao.updateCount(vo);

		// 2) 글 번호 & 아이디에 따른 데이터 리턴
		return dao.selectOneDiary(vo);
	}

	// 다이어리 작성하는 메소드
	@Override
	public int insertDiary(MultipartHttpServletRequest request) {

		// 1) 일기 작성 폼에서 값 가져오기
		// 아이디는 일단 디폴트 값
		String id = "root";
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		// 2) Dao 메소드에서 사용할 매개변수 생성
		DiaryVO vo = new DiaryVO();
		vo.setId(id);
		vo.setTitle(title);
		vo.setContent(content);

		// 3) 이미지 파일 가져오기
		// (1) 폼에서 파일 가져오기
		MultipartFile image = request.getFile("image");
		// (2) 파일 저장할 디렉토리의 절대경로 가져오기 -> servlet 버전이 2.5 이상 이어야 함
		String uploadPath = request.getServletContext().getRealPath("/diaryimage");
		// (3) 중복되지 않는 문자열 생성
		UUID uid = UUID.randomUUID();
		String filename = image.getOriginalFilename();

		if (filename != null) {
			filename = uid + filename;
			// 저장된 파일 경로 생성
			String filepath = uploadPath + "\\" + filename;
			// 파일 업로드
			File file = new File(filepath);
			try {
				image.transferTo(file);

			} catch (Exception e) {
				e.printStackTrace();
			}
			// 테이블에 파일 이름 저장
			vo.setImage(filename);

		} else {

			// 사용자가 선택한 파일이 없을 경우 null로 표시 또는 디폴트 이미지 등록
			vo.setImage("");

		}
		// 4) Dao 메소드 호출
		return dao.insertDiary(vo);
	}

	// 수정 페이지로 이동 - id와 글 번호 받아서 데이터 전달
	@Override
	public DiaryVO goUpdate(DiaryVO vo) {
		// selectOneDiary 재사용
		return dao.selectOneDiary(vo);
	}

	// 글 수정 -> 글 입력과 똑같음
	@Override
	public int updateDiary(MultipartHttpServletRequest request) {
		// 1) 일기 작성 폼에서 값 가져오기
		String id = request.getParameter("id");
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		// 2) Dao 메소드에서 사용하기 위해 VO 가져와서 값 저장
		DiaryVO vo = new DiaryVO();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setId(id);

		// 3) 이미지 파일 가져오기
		// 폼에서 이미지 파일 가져옴
		MultipartFile image = request.getFile("image");
		// 등록한 파일 저장할 디렉토리의 절대 경로 가져오기
		String uploadPath = request.getServletContext().getRealPath("/diaryimage");
		// 유일무이한 문자열 생성
		UUID uid = UUID.randomUUID();
		// 등록된 파일 이름 가져오기
		String filename = image.getOriginalFilename();

		if (filename != null) {
			// 랜덤 문자열 & 파일 이름 합치기
			filename = uid + filename;
			// 저장할 파일 경로 설정
			String filepath = uploadPath + "\\" + filename;
			// 파일 경로 생성
			File file = new File(filepath);
			try {
				// 이미지 파일을 업로드
				image.transferTo(file);

			} catch (Exception e) {
				e.printStackTrace();
			}
			// 테이블에 파일 이름 저장
			vo.setImage(filename);

		} else {
			vo.setImage("");
		}

		// 4) Dao 호출
		return dao.updateDiary(vo);
	}

	// 글 번호 아이디가 맞는 데이터 삭제
	@Override
	public int deleteDiary(DiaryVO vo) {

		return dao.deleteDiary(vo);
	}

}
