<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Pet Diary</title>
</head>
<body>
	<%@include file="../include/header.jsp"%>
	<br />
	<br />
	<br />
	<div class="container-fluid">

		<div class="col-lg-6  col-lg-offset-3">
			<div class="text-center">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h5>
							<strong>My Pet Diary</strong>
						</h5>
					</div>
					<div class="panel-body">
						${msg}<br />
					</div>

				</div>

				<div>
					<div class="box-footer">
						<a href="list" type="button" class="btn btn-primary">목록으로</a>

					</div>
				</div>
			</div>
		</div>


	</div>





	<%@include file="../include/footer.jsp"%>
</body>
</html>