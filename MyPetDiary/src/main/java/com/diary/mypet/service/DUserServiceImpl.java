package com.diary.mypet.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

	// 4. 프로필 조회
	@Override
	public DUserVO selectProfile(String id) {

		return dao.selectProfile(id);
	}

	// 5. 프로필 업데이트
	// 프로필 이미지 & 닉네임 & 소개 받아서 저장
	@Override
	public int updateProfile(MultipartHttpServletRequest request) {
		// 1) 사용자가 입력한 닉네임 / 소개글 가져오기
		String nickname = request.getParameter("nickname");
		String intro = request.getParameter("intro");
		String id = request.getParameter("id");

		// 2) 프로필 업데이트를 위해 DUserVO 생성 후 저장
		DUserVO vo = new DUserVO();
		vo.setNickname(nickname);
		vo.setIntro(intro);
		vo.setId(id);

		// 3) 이미지 파일 가져오기
		// (1) 폼에서 이미지 파일 가져오
		MultipartFile profile = request.getFile("profile");
		// (2) 이미지 저장할 디렉토리의 절대 경로 저장
		String uploadPath = request.getServletContext().getRealPath("/userimage");
		// (3) 중복 되지 않는 문자열 생성
		UUID uid = UUID.randomUUID();
		// (4) 오리지널 파일 이름 가져오기
		String filename = profile.getOriginalFilename();

		if (filename == null || filename.equals("")) {
			// // 선택한 파일이 없다면 디폴트 이미지 이름 저장
			// vo.setProfile("default_profile_dog.png");
			// 수정의 경우 hidden 태그로 value값에 이미지이름을 저장
			String hiddenimg = request.getParameter("hiddenimg");
			vo.setProfile(hiddenimg);

		} else {
			// 선택한 파일이 있는 경우s
			// (5) 선택한 파일의 확장자 저장(파일 이름의 뒤의 3자리 가져오기)
			String ext = filename.substring(filename.length() - 3, filename.length());
			// (6) id + uid + ext 합쳐서 파일이름 생성
			filename = id + uid + "." + ext;
			// (7) 저장할 파일 경로 생성
			String path = uploadPath + "/" + filename;
			System.out.println("파일경로 : " + path);
			// (8) 파일 업로드
			File file = new File(path);
			// (9) 프로필 이미지 파일로 전환
			try {
				profile.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// (10) 테이블에 파일이름 저장
			vo.setProfile(filename);

		}
		// 4) dao 메소드 호출
		return dao.updateProfile(vo);
	}

	// 6. 회원 탈퇴
	@Override
	public int deleteUser(String id) {

		return dao.deleteUser(id);
	}

}
