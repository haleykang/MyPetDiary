<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@include file="include/header.jsp"%>


<c:if test="${msg != null}">
	<div class="alert alert-success alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<strong>[알림]&nbsp;</strong>${msg}
	</div>
</c:if>

<div class="jumbotron"
	style="background-image: url('/mypet/resources/bootstrap/img/main_img.jpg')">
	<div class="row">
		<div class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-1">
			<h1 style="color: white">My Pet Diary</h1>

			<p style="color: white">
				오늘 당신과 당신의 반려동물은 어떤 하루를 보냈나요?<br />소중한 하루를 기록하고 공유하세요.
			</p>
			<p>
				<a class="btn btn-primary btn-lg" href="diary/list">시작하기<span
					class="glyphicon glyphicon-arrow-right"></span></a>
			</p>
		</div>
	</div>
</div>


<%@include file="include/footer.jsp"%>

