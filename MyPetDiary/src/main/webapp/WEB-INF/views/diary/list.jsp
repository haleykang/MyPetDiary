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
	<!-- 목록 보기 페이지  -->

	<!-- 타이틀 -->
	<!-- 타이틀 -->

	<div class="row">
		<div class="col-lg-6  col-lg-offset-3">
			<div class="page-header">
				<h3 id="forms">Diary</h3>
			</div>
		</div>
	</div>



	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">

				<div class="post-preview">
					<a href="post.html">
						<h2 class="post-title">Man must explore, and this is
							exploration at its greatest</h2>
						<h3 class="post-subtitle">Problems look mighty small from 150
							miles up</h3>
					</a>
					<p class="post-meta">
						Posted by <a href="#">Start Bootstrap</a> on September 24, 2014
					</p>
				</div>
				<hr>
				<div class="post-preview">
					<a href="post.html">
						<h2 class="post-title">I believe every human has a finite
							number of heartbeats. I don't intend to waste any of mine.</h2>
					</a>
					<p class="post-meta">
						Posted by <a href="#">Start Bootstrap</a> on September 18, 2014
					</p>
				</div>
				<hr>
				<div class="post-preview">
					<a href="post.html">
						<h2 class="post-title">Science has not yet mastered prophecy
						</h2>
						<h3 class="post-subtitle">We predict too much for the next
							year and yet far too little for the next ten.</h3>
					</a>
					<p class="post-meta">
						Posted by <a href="#">Start Bootstrap</a> on August 24, 2014
					</p>
				</div>
				<hr>
				<div class="post-preview">
					<a href="post.html">
						<h2 class="post-title">Failure is not an option</h2>
						<h3 class="post-subtitle">Many say exploration is part of our
							destiny, but it’s actually our duty to future generations.</h3>
					</a>
					<p class="post-meta">
						Posted by <a href="#">Start Bootstrap</a> on July 8, 2014
					</p>
				</div>
				<hr>
				<!-- Pager -->
				<ul class="pager">
					<li class="next"><a href="#">Older Posts &rarr;</a></li>
				</ul>
			</div>
		</div>
	</div>


	<!-- 글 쓰기 버튼 -->


	<%@include file="../include/footer.jsp"%>
</body>
</html>