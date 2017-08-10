package com.diary.mypet.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.diary.mypet.dao.DiaryDao;
import com.diary.mypet.domain.DUserVO;

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
		// 아이디는 세션에서 가져
		HttpSession session = request.getSession();
		DUserVO userVO = (DUserVO) session.getAttribute("login");
		String id = userVO.getId();
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		// 1-2) 체크 박스 추가 된 값 가져오기
		String ckshare = request.getParameter("ckshare");
		System.out.println(ckshare);

		// 2) Dao 메소드에서 사용할 매개변수 생성
		DiaryVO vo = new DiaryVO();
		vo.setId(id);
		vo.setTitle(title);
		vo.setContent(content);

		if (ckshare == null || ckshare.equals("")) {

			vo.setCkshare("false");

		} else {
			vo.setCkshare(ckshare);
		}

		// 3) 이미지 파일 가져오기
		// (1) 폼에서 파일 가져오기
		MultipartFile image = request.getFile("image");
		// (2) 파일 저장할 디렉토리의 절대경로 가져오기 -> servlet 버전이 2.5 이상 이어야 함
		String uploadPath = request.getServletContext().getRealPath("/diaryimage");
		// (3) 중복되지 않는 문자열 생성
		UUID uid = UUID.randomUUID();
		String filename = image.getOriginalFilename();
		// 인코딩 안되니까 가져온 파일이름의 뒤 3글자 가져와서 파일 이름에 붙이자
		System.out.println("Original filename : " + filename);

		// String ext = filename.substring(filename.length() - 3, filename.length());
		// System.out.println("확장자 : " + ext);

		// System.out.println("filename = " + filename);
		// String filename = null;
		//
		// try {
		//
		// filename = URLEncoder.encode(image.getOriginalFilename(), "utf-8");
		// System.out.println("filename = " + filename);
		// } catch (UnsupportedEncodingException e) {
		//
		// e.printStackTrace();
		// // TODO: handle exception
		// }

		if (filename == null || filename.equals("")) {
			// 사용자가 선택한 파일이 없을 경우 null로 표시 또는 디폴트 이미지 등록
			vo.setImage("default_image.jpg");

		} else {

			String ext = filename.substring(filename.length() - 3, filename.length());
			System.out.println("확장자 : " + ext);

			filename = id + uid + "." + ext;
			// 저장된 파일 경로 생성
			String filepath = uploadPath + "/" + filename;
			System.out.println("filename : " + filename);
			System.out.println("filepath : " + filepath);
			// 파일 업로드
			File file = new File(filepath);
			try {
				image.transferTo(file);

			} catch (Exception e) {
				e.printStackTrace();
			}
			// 테이블에 파일 이름 저장
			vo.setImage(filename);
		}

		// *** 중요한 수정사항 ***
		// filename.equals("") 로도 해야함

		// if (filename != null) {
		// // 랜덤 문자열 & 파일 이름 합치기
		// filename = id + uid;
		// // 저장할 파일 경로 설정
		// String filepath = uploadPath + "/" + filename;
		//
		// // 파일 경로 생성
		// File file = new File(filepath);
		// try {
		// // 이미지 파일을 업로드
		// image.transferTo(file);
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// // 테이블에 파일 이름 저장
		// vo.setImage(filename);
		//
		// } else {
		// // 오리지널 파일 이름이 null이면 -> iimmgg 태그에 있는 값을 가져와서 저장 - 기존 사진 그대로 흠..
		// vo.setImage("default_image.jpg");
		// }

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

		// 1-2) 체크 박스 추가 된 값 가져오기
		String ckshare = request.getParameter("ckshare");
		System.out.println(ckshare);

		// 2) Dao 메소드에서 사용할 매개변수 생성
		DiaryVO vo = new DiaryVO();
		vo.setNo(no);
		vo.setId(id);
		vo.setTitle(title);
		vo.setContent(content);

		if (ckshare == null || ckshare.equals("")) {

			vo.setCkshare("false");

		} else {
			vo.setCkshare(ckshare);
		}

		// 3) 이미지 파일 가져오기
		// String test = request.getParameter("iimmgg");
		// System.out.println(test);

		// 폼에서 이미지 파일 가져옴
		MultipartFile image = request.getFile("image");
		// 등록한 파일 저장할 디렉토리의 절대 경로 가져오기
		String uploadPath = request.getServletContext().getRealPath("/diaryimage");
		// 유일무이한 문자열 생성
		UUID uid = UUID.randomUUID();
		// 등록된 파일 이름 가져오기

		String filename = image.getOriginalFilename();
		System.out.println("Original filename : " + filename);

		// String filename = image.getOriginalFilename();
		// System.out.println("오리지널 파일 이름 : " + filename);

		if (filename == null || filename.equals("")) {

			String iimmgg = request.getParameter("iimmgg");
			System.out.println("iimmgg : " + iimmgg);
			vo.setImage(iimmgg);

		} else {

			String ext = filename.substring(filename.length() - 3, filename.length());
			System.out.println("확장자 : " + ext);

			filename = id + uid + "." + ext;
			// 저장된 파일 경로 생성
			String filepath = uploadPath + "/" + filename;
			System.out.println("filepath : " + filepath);
			System.out.println("filename : " + filename);
			// 파일 업로드
			File file = new File(filepath);
			try {
				image.transferTo(file);

			} catch (Exception e) {
				e.printStackTrace();
			}
			// 테이블에 파일 이름 저장
			vo.setImage(filename);

		}

		// if (filename != null) {
		// // 랜덤 문자열 & 파일 이름 합치기
		// filename = id + uid;
		// // 저장할 파일 경로 설정
		// String filepath = uploadPath + "/" + filename;
		//
		// // 파일 경로 생성
		// File file = new File(filepath);
		// try {
		// // 이미지 파일을 업로드
		// image.transferTo(file);
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// // 테이블에 파일 이름 저장
		// vo.setImage(filename);
		//
		// } else {
		// // 오리지널 파일 이름이 null이면 -> iimmgg 태그에 있는 값을 가져와서 저장 - 기존 사진 그대로 흠..
		// vo.setImage("default_image.jpg");
		// }

		// 4) Dao 호출
		return dao.updateDiary(vo);
	}

	// 글 번호 아이디가 맞는 데이터 삭제
	@Override
	public int deleteDiary(DiaryVO vo) {

		return dao.deleteDiary(vo);
	}

	// 모두의 다이어리
	@Override
	public List<DiaryVO> shareAllDiary() {

		return dao.shareAllDiary();
	}

}