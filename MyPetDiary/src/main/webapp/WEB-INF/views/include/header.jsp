<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- session 설정 true 로 변경 -->
<%@ page session="true"%>

<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>My Pet Diary</title>
<!-- 부트스트랩 -->
<link href="/mypet/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/mypet/resources/bootstrap/js/bootstrap.min.js"></script>
<body>

	<script>
		//// html dom 이 다 로딩된 후 실행
		$(document).ready(function() {
			// dropdown 클래스 바로 하위에 있는 a 태그를 클릭 했을 때
			$(".dropdown>a").click(function() {
				// 다음 ul클래스 저장
				var submenu = $(this).next("ul");

				// submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게 펼치기
				if (submenu.is(":visible")) {
					submenu.slideUp();
				} else {
					submenu.slideDown();
				}
			});
		});
	</script>



	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/mypet">My Pet Diary</a>
			</div>




			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<!-- 드롭 -->

				<ul class="nav navbar-nav navbar-right">

					<li class="dropdown"><a id="drop" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false"><span
							class="glyphicon glyphicon-menu-hamburger text-primary">&nbsp;Menu</span>&nbsp;<span
							class="caret text-primary"></span> </a>

						<ul class="dropdown-menu" role="menu">

							<li role="presentation" class="dropdown-header">모두의 공간</li>
							<li><a href="/mypet/diary/shareboard"><span
									class="glyphicon glyphicon-star text-warning"></span>&nbsp;모두의
									일기</a></li>

							<!-- 로그인 전에는 안보이도록.. -->
							<c:if test="${login != null}">
								<li class="divider"></li>
								<li role="presentation" class="dropdown-header">나만의 공간</li>
								<li><a href="/mypet/diary/list"><span
										class="glyphicon glyphicon-book text-success"></span>&nbsp;일기장</a></li>


							</c:if>

						</ul></li>
				</ul>



				<!-- 	<ul class="nav navbar-nav navbar-right">
					<li><a href="/mypet/diary/shareboard"><span
							class="glyphicon glyphicon-star text-primary">&nbsp;모두의 일기</span></a></li>
				</ul> -->
				<c:if test="${login == null}">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="/mypet/user/login"><span
								class="glyphicon glyphicon-ok text-success">&nbsp;Login</span></a></li>
					</ul>
				</c:if>
				<c:if test="${login != null}">

					<ul class="nav navbar-nav navbar-right">
						<li><a href="/mypet/user/logout"><span
								class="glyphicon glyphicon-remove text-warning">&nbsp;Logout</span></a></li>
					</ul>

				</c:if>



			</div>
		</div>
	</nav>

</body>
</html>