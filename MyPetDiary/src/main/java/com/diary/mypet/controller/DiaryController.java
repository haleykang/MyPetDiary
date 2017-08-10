package com.diary.mypet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diary.mypet.domain.DUserVO;
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

	}

	// 2. 일기 작성 후 테이블에 저장하는 메소드
	// 이미지 업로드를 위해 매개 변수 MultipartHttpSerlvetRequest로 변경
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String writePost(MultipartHttpServletRequest request, RedirectAttributes attr) {

		// 1) 사용자가 작성한 id(일단 default 'root'), title, content 테이블에 입력
		int result = service.insertDiary(request);

		if (result < 1) {

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
	public void goList(Model model, HttpServletRequest request) {
		// 1) 아이디 기준으로 테이블 데이터 가져오는 함수 실행 후 리스트에 저장
		// 세션에 저장된 아이디 가져오기
		HttpSession session = request.getSession();
		DUserVO login = (DUserVO) session.getAttribute("login");
		String id = login.getId();
		// System.out.println(id);

		List<DiaryVO> list = service.selectAllDiary(id);
		// 2) 리스트 페이지에 값 전달하기 위해서 모델 객체에 저장
		model.addAttribute("list", list);
		// 3) diary/list.jsp 페이지로 이동

	}

	// 5. 상세보기 처리 메소드 - 상세보기 페이지로 이동
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public void detail(@RequestParam("no") int no, @RequestParam("id") String id, Model model) {
		// 1) 서비스 함수의 매개변수로 사용하기 위해 DiaryVO 객체 생성
		DiaryVO vo = new DiaryVO();
		vo.setId(id);
		vo.setNo(no);

		// 2) 1번을 매개 변수로 일치하는 데이터를 출력하는 함수 실행 후 저장
		DiaryVO detail = service.selectOneDiary(vo);

		// 3) model 객체에 저장
		model.addAttribute("detail", detail);

		// 4) detail 페이지로 이동
	}

	// 6. 삭제 처리 메소드
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam("no") int no, @RequestParam("id") String id, RedirectAttributes attr) {

		// 1) 서비스 매개 변수로 사용할 VO 클래스 가져와서 no와 id 저장
		DiaryVO vo = new DiaryVO();
		vo.setNo(no);
		vo.setId(id);

		// 2) 삭제 함수 실행
		int res = service.deleteDiary(vo);
		System.out.println("Result for delete : " + res);

		// 3) 삭제 메세지 저장
		attr.addFlashAttribute("msg", "일기를 삭제하였습니다.");

		// 4) 결과 페이지로 이동
		return "redirect:result";

	}

	// 7. 수정 페이지로 이동 메소드
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public void goUpdate(@RequestParam("no") int no, @RequestParam("id") String id, Model model) {
		// 1) 서비스 매개 변수 VO 클래스에 저장 -> 귀찮음...
		DiaryVO vo = new DiaryVO(no, id);
		// 2) 서비스 메소드 실행
		DiaryVO detail = service.goUpdate(vo);
		// 3) 모델 객체에 데이터 저작
		model.addAttribute("detail", detail);
		// 4) 수정 페이지로 이동
	}

	// 8. 게시글 수정 메소드
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(MultipartHttpServletRequest request, RedirectAttributes attr) {

		// 1) service 수정 메소드 호출
		int result = service.updateDiary(request);
		System.out.println("Result for update : " + result);

		// 2) 수정 성공 메세지 저장
		attr.addFlashAttribute("msg", "일기를 수정하였습니다.");

		// 3) 결과 페이지로 리다이렉트
		return "redirect:result";
	}

}