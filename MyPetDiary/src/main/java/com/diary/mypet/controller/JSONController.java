package com.diary.mypet.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diary.mypet.service.DUserService;

// 결과를 JSON문자열로 전달하는 컨트롤러 
@RestController
public class JSONController {

	@Autowired
	private DUserService userService;

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

}
