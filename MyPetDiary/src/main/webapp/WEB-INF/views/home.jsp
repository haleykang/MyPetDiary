<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Pet Diary</title>
</head>
<body>
	<%@include file="include/header.jsp"%>

	<div class="jumbotron">
		<div class="row">
			<div class="col-md-6 col-md-offset-1">
				<h1>
					<em>My Pet Diary</em>
				</h1>


				<p class="text-muted">
					<small>당신의 반려동물과 어떤 하루를 보냈나요? <br />소중한 일상을 공유해보세요.
					</small>
				</p>

				<br />
				<p>
					<a class="btn btn-info btn-lg" href="#">시작하기</a>
				</p>
			</div>
		</div>
	</div>

	<%@include file="include/footer.jsp"%>
</body>
</html>