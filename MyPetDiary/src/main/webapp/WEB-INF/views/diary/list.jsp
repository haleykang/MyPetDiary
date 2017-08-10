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
		<div align="left">
			<h4>일기 목록</h4>
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
		<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">


			<c:if test="${fn:length(list) < 1}">

				<div>
					<h5>등록된 일기가 없습니다. 일기를 작성해주세요.</h5>

				</div>

			</c:if>

			<!-- 반복문 시작 -->
			<c:forEach items="${list}" var="list">

				<div class="media">
					<c:if test="${list.image == null}">
						<div class="media-left">
							<a href="detail?no=${list.no}&id=${list.id}"> <img
								class="media-object" src="">
							</a>
						</div>

					</c:if>
					<c:if test="${list.image != null}">

						<div class="col-xs-6 col-md-3">
							<a href="detail?no=${list.no}&id=${list.id}" class="thumbnail">
								<img src="../diaryimage/${list.image}">
							</a>
						</div>
					</c:if>
					<div class="media-body">
						<a href="detail?no=${list.no}&id=${list.id}">
							<h4 class="media-heading">${list.title}</h4>
						</a> Posted by <span class="text-primary">${list.id}</span>&nbsp;&nbsp;작성일
						${list.regdate}
					</div>
				</div>
				<hr>
			</c:forEach>

			<!-- 반복문 종료 -->


			<!-- Pager -->
			<!--   <ul class="pager">
				<li class="next"><a href="#">Older Posts &rarr;</a></li>
			</ul> -->

		</div>
	</div>


	<%@include file="../include/footer.jsp"%>
</body>
</html>