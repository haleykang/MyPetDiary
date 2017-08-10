package com.diary.mypet.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// 로그인 인터셉터

public class LoginInterceptor implements HandlerInterceptor {

	// 요청 처리 후 뷰 페이지 이동 후
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	// 요청 처리 후 뷰 페이지 이동 전
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

		// 1) 로그인 여부를 확인하기 위해 컨트롤러에서 모델에 저장한 정보 가져오기
		ModelMap modelMap = arg3.getModelMap();

		// 2) Object 객체에 모델에서 가져온 login 정보 저장
		Object login = modelMap.get("login");

		// 3) 로그인 성공 여부 판단
		if (login == null) {
			// 로그인 실패
			// 로그인 페이지로 리다이렉트
			HttpSession session = arg0.getSession();
			session.setAttribute("loginmsg", "false");
			arg1.sendRedirect("login");

		} else {
			// 로그인 성공
			// (1) session에 로그인 정보 저장
			HttpSession session = arg0.getSession();
			session.setAttribute("login", login);
			// (2) 요청이 들어온 주소 확인
			String dest = (String) session.getAttribute("dest");
			if (dest == null) {
				arg1.sendRedirect("/mypet");
			} else {
				arg1.sendRedirect(dest);
			}

		}

	}

	// 요청 처리 전
	// true 리턴 - Controller로 가서 다음 요청 처리
	// false 리턴 - Controller로 이동 안함
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {

		// 로그인 처리 전 기존 로그인 정보가 있으면 삭제

		// 1) 세션 가져오기
		HttpSession session = arg0.getSession();
		// 2) 로그인 정보가 있으면 삭제
		if (session != null) {
			session.removeAttribute("login");
			session.removeAttribute("loginmsg");
		}

		// 3) 컨트롤러가 요청 처리
		return true;
	}

}