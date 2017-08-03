package com.diary.mypet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diary.mypet.domain.DiaryVO;
import com.diary.mypet.service.DiaryService;

// diary/로 시작되는 요청 처리 컨트롤러
@Controller
@RequestMapping("diary/*")
public class DiaryController {

	@Autowired
	private DiaryService service;

	// 1. 글쓰기 페이지로 이동하는 메소드
	@RequestMapping(value = "write", method = RequestMethod.GET)
	public void writeGet() {
		// /board/write.jsp로 단순 이동 메소드
	}

	// 2. 일기 작성 후 테이블에 저장하는 메소드
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String writePost(DiaryVO vo, RedirectAttributes attr) {

		// 1) 사용자가 작성한 id(일단 default 'root'), title, content 테이블에 입력
		int result = service.insertDiary(vo);

		if (result < 0) {

			// 글 쓰기 실패시 다시 글 쓰기 페이지로
			return "redirect:write";

		} else {

			// 글 쓰기 성공시 성공 결과 페이지로 이동
			// 이동하면서 글 작성 성공 메세지 저장해서 보내기
			attr.addFlashAttribute("msg", "일기가 등록되었습니다.");
			return "redirect:result";
		}

	}

	// 3. 글 쓰기/글 수정/글 삭제 후 성공 메세지 보여주는 result.jsp로 이동하는 메소드
	@RequestMapping(value = "result", method = RequestMethod.GET)
	public void toResult() {

	}

	// 4. 모아 보기 화면으로 이동하는 메소드
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void goList(Model model) {
		// 1) 아이디 기준으로 테이블 데이터 가져오는 함수 실행 후 리스트에 저장
		List<DiaryVO> list = service.selectAllDiary("root");
		// 2) 리스트 페이지에 값 전달하기 위해서 모델 객체에 저장
		model.addAttribute("list", list);
		// 3) diary/list.jsp 페이지로 이동

	}

}
