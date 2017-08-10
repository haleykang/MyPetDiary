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




	<div class="container">
		<!-- 경고 창 -->
		<c:if test="${loginmsg != null}">

			<div class="alert alert-default alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>로그인 오류!&nbsp;</strong> 로그인 정보를 다시 한 번 확인해주세요!
			</div>

			<!-- 경고 창 끝 -->
		</c:if>

		<c:if test="${msg != null}">

			<div class="alert alert-default alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>회원 가입에 성공하였습니다!&nbsp;</strong> 로그인을 진행해주세요.
			</div>

			<!-- 경고 창 끝 -->
		</c:if>

		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-10 col-sm-offset-2">
			<div class="panel panel-warning">
				<div class="panel-heading">
					<div class="panel-title">로그인</div>

				</div>

				<div style="padding-top: 30px" class="panel-body">

					<!-- 로그인 폼 -->
					<form class="form-horizontal" action="loginPost" method="post">

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input type="text"
								class="form-control" name="id" required
								placeholder="아이디를 입력하세요.">
						</div>

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input type="password"
								class="form-control" name="pw" required
								placeholder="비밀번호를 입력하세요.">
						</div>


						<div style="margin-top: 10px" class="form-group">
							<!-- Button -->

							<div class="col-sm-12 controls">
								<div class="text-center">
									<button type="submit" class="btn btn-primary">로그인</button>
								</div>
							</div>
						</div>


						<div class="form-group">
							<div class="col-md-12 control">
								<div
									style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
									아직 회원이 아니신가요?&nbsp;&nbsp;<a href="join" class="">회원가입</a>
								</div>
							</div>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>





	<%@include file="../include/footer.jsp"%>
</body>


</html>