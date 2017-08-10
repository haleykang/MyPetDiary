<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Pet Diary</title>
</head>
<!-- 상세보기 화면  -->
<body>
	<!-- 상단 네비 -->
	<%@include file="../include/header.jsp"%>

	<!-- 타이틀 -->
	<div class="bs-docs-section">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
				<div class="page-header">
					<h4>일기 보기</h4>
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">


				<div class="box-body">
					<!-- name의 값은 테이블 이름과 일치 시키기 -->
					<label class="control-label"><strong>제목</strong></label> <input
						type="text" name="title" class="form-control"
						value="${detail.title}" readonly="readonly" />
				</div>
				<br />

				<div class="box-body">
					<label class="control-label"><strong>내용</strong></label>
					<textarea class="form-control" name="content" rows="10"
						readonly="readonly">${detail.content}</textarea>
				</div>
				<br />


				<!-- 조회수 작성자 작성일 -->
				<div class="text-right">

					<p>
						<span class="glyphicon glyphicon-heart text-danger"></span>&nbsp;조회수
						: ${detail.readcnt}&nbsp;&nbsp;&nbsp;<span
							class="glyphicon glyphicon-user text-warning"></span>&nbsp;작성자 :
						${detail.id}&nbsp;&nbsp;&nbsp;<span
							class="glyphicon glyphicon-calendar text-info"></span>&nbsp;작성일 :
						${detail.regdate}
					</p>

				</div>


				<hr>
				<div>
					<label class="control-label"><strong>오늘의 이미지</strong></label>
					<c:if test="${detail.image != null}">
						<div class="well" style="background: white; border: 1px;">
							<br /> <img class="img-responsive center-block" id="image"
								src="../diaryimage/${detail.image}" />
						</div>
					</c:if>
				</div>
				<br />
				<div class="form-group">
					<c:if test="${detail.ckshare == 'true' }">
						<input type="checkbox" name="ckshare" value="true"
							checked="checked" disabled="disabled">&nbsp;<span
							class="glyphicon glyphicon-share text-primary"></span>&nbsp;작성한
							일기를 게시판에 공유합니다.
					</c:if>
					<c:if test="${detail.ckshare == 'false' }">
						<input type="checkbox" name="ckshare" value="true" disabled="disabled"
							>&nbsp;<span
							class="glyphicon glyphicon-share text-primary"></span>&nbsp;작성한
							일기를 게시판에 공유합니다.
					</c:if>
				</div>


				<div class="box-footer">
					<div>
						<hr>
						<div class="text-center">
							<!-- 필요 버튼 선언 -->
							<c:if test="${login.id == detail.id}">
								<!-- 진입한 위치에 따라 다른 페이지로 이동  -->
								<button class="btn btn-default" onclick="history.back();">돌아가기</button>
								<button id="update" class="btn btn-primary">수정</button>
								<button id="delete" class="btn btn-danger">삭제</button>
							</c:if>
							<c:if test="${login.id != detail.id}">
								<button class="btn btn-default" onclick="history.back();">돌아가기</button>
							</c:if>


						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<script>
		/* 	$(function() {
				$('#list').on('click', function() {
					location.href = "list";
				});
			});
		 */
		// 수정
		$(function() {
			$('#update').on('click', function() {
				location.href = "update?no=${detail.no}&id=${detail.id}";
			});
		});

		// 삭제
		$(function() {
			$('#delete').on('click', function() {
				location.href = "delete?no=${detail.no}&id=${detail.id}";
			});
		});
	</script>

	<!-- 하단 카피  -->
	<%@include file="../include/footer.jsp"%>
</body>
</html>