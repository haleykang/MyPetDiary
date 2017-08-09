package com.diary.mypet.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// 접근 사용자 권한 여부 확인

public class AuthInterceptor implements HandlerInterceptor {

	// 뷰페이지 이동 후
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	// 요청 처리 후 뷰 페이지 이동 전
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	// 컨트롤러 실행 전
	// 요청으로 이동하기 전 로그인이 되어있는지 여부 확인
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// 1) 세션 가져오기
		HttpSession session = arg0.getSession();
		// 2) 로그인 여부 확인
		if (session.getAttribute("login") == null) {
			// (1) 로그인 페이지로 이동 전 요청이 들어온 경로 미리 저장
			String uri = arg0.getRequestURI();
			// (2) 파라미터 문자열 가져오기 -> 뒤에 추가된 파라미터 들
			String query = arg0.getQueryString();
			// (3) 쿼리가 없으면 "" 있다면 ? 형태로 추가
			if (query == null || query.equals("null")) {
				query = "";
			} else {
				query = "?" + query;
			}
			// (4) 세션에 dest 키로 uri와 쿼리 저장
			session.setAttribute("dest", uri + query);

			// (5) 로그인 페이지로 리다이렉트
			arg1.sendRedirect("/mypet/user/login");

			// (6) 컨트롤러 처리 안함
			return false;

		}
		// 로그인 된 상태라면 컨트롤러 처리
		return true;
	}

}
