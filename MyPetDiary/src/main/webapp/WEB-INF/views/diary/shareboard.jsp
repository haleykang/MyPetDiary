<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 반복문 사용을 위해서 태그 라이브러리 추가 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 사이즈 체크를 위해  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Pet Diary</title>
</head>
<body>
	<%@include file="../include/header.jsp"%>


	<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
		<div class="text-center">
			<h4>
				<strong>모두의 일기</strong>
			</h4>
			<p>나의 일기를 공유하고, 다른 사람의 일기를 읽어봐요.</p>
		</div>
		<br />
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">모두의 일기</div>

			<div class="table-responsive">
				<!-- Table -->
				<table class="table table-striped table-hover">
					<!-- 목록보기 타이틀 생성 -->
					<thead>
						<tr>
							<th>No.</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회</th>
						</tr>
					</thead>

					<!-- 글 목록 출력을 위한 반복문 작성 -->
					<!-- ${list}의 데이터를 순회하면서 순서대로 vo에 저장 -->
					<tbody>
						<c:forEach var="vo" items="${list}">
							<tr>
								<td>${vo.no}</td>
								<!-- 상세보기를 위해 제목에 링크 설정 & 파라미터로 bno값 넘겨주기 -->
								<td><a href="detail?no=${vo.no}&id=${vo.id}">${vo.title}</a></td>
								<td>${vo.id}</td>
								<td>${vo.regdate}</td>
								<td align="right"><span class="badge">${vo.readcnt}&nbsp;</span></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>


		<!-- 버튼 추가 -->

		<div class="box-footer">
			<div class="text-center">
				<a href="write" class="btn btn-default">일기쓰기</a>
			</div>
		</div>
	</div>


	<br />
	<br />




	<%@include file="../include/footer.jsp"%>
</body>
</html>