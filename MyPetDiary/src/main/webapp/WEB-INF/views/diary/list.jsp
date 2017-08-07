<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 반복문 사용을 위해서 태그 라이브러리 추가 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Pet Diary</title>
</head>
<body>
	<%@include file="../include/header.jsp"%>
	<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
		<div align="left">
			<h3>일기 목록</h3>
		</div>
		<div align="right">
			<p>
				<a href="write" class="btn btn-link"><span
					class="glyphicon glyphicon-pencil"></span>일기 작성</a>
			</p>
		</div>
		<hr>

	</div>

	<!-- Main Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">

				<!-- 반복문 시작 -->
				<c:forEach items="${list}" var="list">
					<div class="row">
						<!-- 이미지가 있을 때만 보여주기 -->
						<!-- 안이쁘니까 디폴트 이미지를 추가할까? -->
						<c:if test="${list.image != null}">
							<div class="col-xs-6 col-md-3">
								<a href="detail?no=${list.no}&id=${list.id}" class="thumbnail">
									<img src="../diaryimage/${list.image}">
								</a>
							</div>
						</c:if>
						<div>
							<a href="detail?no=${list.no}&id=${list.id}">
								<h3>
									<strong>${list.title}</strong>
								</h3>
							</a>
							<p>
								Posted by <span class="text-primary">${list.id}</span>&nbsp;&nbsp;작성일
								${list.regdate}
							</p>

						</div>
					</div>
					<hr>
				</c:forEach>
				<!-- 반복문 끝 -->

				<!-- Pager -->
				<ul class="pager">
					<li class="next"><a href="#">Older Posts &rarr;</a></li>
				</ul>
			</div>
		</div>
	</div>

	<%@include file="../include/footer.jsp"%>
</body>
</html>