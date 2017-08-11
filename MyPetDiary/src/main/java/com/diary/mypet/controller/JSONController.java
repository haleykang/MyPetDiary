package com.diary.mypet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diary.mypet.domain.DUserVO;
import com.diary.mypet.domain.DiaryVO;
import com.diary.mypet.service.DUserService;
import com.diary.mypet.service.DiaryService;

// 결과를 JSON문자열로 전달하는 컨트롤러 
@RestController
public class JSONController {

	@Autowired
	private DUserService userService;

	@Autowired
	private DiaryService diaryService;

	// 1.요청 페이지에서 아이디를 가져와서 아이디 중복 확인 후 MAP으로 반환
	@RequestMapping(value = "user/idCheck", method = RequestMethod.GET)
	public Map<String, Object> idCheck(@RequestParam("id") String id) {
		// 1) 서비스 메소드 실행해서 아이디 중복 여부 저장
		boolean result = userService.idCheck(id);
		// 2) Map에 result 값 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		// 3) map 리턴
		return map;
	}

	/// 모바일 기능 구현
	// 1. 모바일 로그인 - 모바일에서 입력받은 아이디 & 비밀번호가 일치하면 정보를 아니면 null 리턴
	@RequestMapping("androidlogin")
	public Map<String, Object> login(@RequestParam("id") String id, @RequestParam("pw") String pw) {

		// 1) 가져온 id와 pw를 diaryVO에 저장 후 service.login 메소드 호출
		DUserVO vo = new DUserVO(id, pw);

		// 2) 서비스 메소드 실행
		DUserVO login = userService.login(vo);

		// 3) 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", login);

		// 4) 맵을 리턴
		return map;

		// 리턴 값 미리 확인
		// 1. 정상 로그인 - http://localhost:8080/mypet/androidlogin?id=kjj8032&pw=1234
		// {"login":{"id":"kjj8032","pw":"1234","profile":null,"nickname":"강남이언니"}}
		// 2. 로그인 실패 - http://localhost:8080/mypet/androidlogin?id=kjj8032&pw=12345
		// {"login":null}
		// 아이피 : 192.168.25.46
	}

	// 2. 모바일 목록 보기
	@RequestMapping("androidlist")
	public Map<String, Object> list(@RequestParam("id") String id) {

		List<DiaryVO> list = diaryService.selectAllDiary(id);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", list);

		return map;
		// 출력 예제
		// http://localhost:8080/mypet/androidlist?id=kjj8032
		/*
		 * {"result":[{"no":108,"title":"안녕하세요","content":"sdf","image":
		 * "default_image.jpg","readcnt":16,"regdate":"2017-08-10","id":"kjj8032",
		 * "ckshare":"true"}]}
		 */
		// 게시글 없을 때
		// {"result":[]}

	}

	// 3. 모바일 상세보기
	@RequestMapping("androiddetail")
	public Map<String, Object> detail(@RequestParam("no") int no, @RequestParam("id") String id) {

		DiaryVO vo = new DiaryVO();
		vo.setNo(no);
		vo.setId(id);

		DiaryVO detail = diaryService.selectOneDiary(vo);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", detail);

		return map;
		// 출력 형식
		// http://localhost:8080/mypet/androiddetail?no=115&id=kjj8032
		/*
		 * {"result":{"no":115,"title":"12","content":"12","image":"default_image.jpg",
		 * "readcnt":2,"regdate":"2017-08-11","id":"kjj8032","ckshare":"true"}}
		 */

	}
}
