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
<!-- 프로필 css -->
<link href="/mypet/resources/bootstrap/css/profile.css" rel="stylesheet">
<title>My Pet Diary</title>
</head>
<body>


	<%@include file="../include/header.jsp"%>


	<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1">
		<section class="content-header">
		<h4>일기목록</h4>
		<ol class="breadcrumb text-left">
			<li><a href="write"><i class="fa fa-dashboard"></i><span
					class="glyphicon glyphicon-pencil text-primary">&nbsp;</span>일기 작성
			</a></li>
			<li class="active">오늘의 일기</li>
		</ol>
		</section>
		<br />
	</div>

	<!-- 사이드 프로필 -->
	<div class="container">
		<div class="row profile">
			<div class="col-md-3">
				<div class="profile-sidebar">
					<!-- SIDEBAR USERPIC -->

					<!-- 기본 프로필 -->
					<div id="main_profile">
						<div class="profile-userpic">
							<img class="img-responsive" id="profile">
						</div>
						<!-- END SIDEBAR USERPIC -->
						<!-- SIDEBAR USER TITLE -->
						<div class="profile-usertitle">
							<div class="profile-usertitle-name" id="nick">닉네임</div>
							<div class="profile-usertitle-job" id="intro">소개를 등록하세요.</div>
						</div>
					</div>

					<!-- 프로필 수정화면  -->
					<div id="edit_profile" style="display: none;">
						<form method="post" enctype="multipart/form-data"
							action="/mypet/user/update">
							<div class="profile-userpic text-right">
								<img class="img-responsive" id="inputprofile"> <label
									class="label label-info btn btn-file">사진 변경<input
									type="file" id="inputimg" name="profile" style="display: none;">
									<input type="hidden" id="hiddenimg" name="hiddenimg" />
								</label>
							</div>
							<!-- END SIDEBAR USERPIC -->
							<!-- SIDEBAR USER TITLE -->
							<div class="profile-usertitle">
								<div class="profile-usertitle-name">
									<input class="form-control" type="text" name="nickname"
										id="inputnick" placeholder="닉네임을 입력해주세요." required
										maxlength="30" /> <input type="hidden" id="id" name="id"
										value="${login.id}" />
								</div>
								<div class="profile-usertitle-job">
									<textarea rows="2" class="form-control" required
										placeholder="소개를 입력해주세요." id="inputintro" name="intro"></textarea>
								</div>
								<div class="profile-userbuttons text-center">
									<button type="submit" onclick="goProfile()"
										class="btn btn-success">수정 완료</button>
								</div>

							</div>
						</form>
					</div>


					<!-- END SIDEBAR USER TITLE -->
					<!-- SIDEBAR BUTTONS -->
					<!-- <div class="profile-userbuttons text-right">
						<a href="#" class="btn btn-link">프로필 수정</a>
					</div> -->
					<!-- END SIDEBAR BUTTONS -->
					<!-- SIDEBAR MENU -->
					<div class="profile-usermenu">
						<ul class="nav">
							<li class="active"><a href="list"> <i
									class="glyphicon glyphicon-book"></i> 내 일기장
							</a></li>
							<li><a onclick="goEditProfile()"><i
									class="glyphicon glyphicon-user"></i> 프로필 설정 </a></li>
							<li><a onclick="$('#deletealert').show(500)"><i
									class="glyphicon glyphicon-remove"></i> 회원 탈퇴 </a></li>


						</ul>
						<div id="deletealert" class="alert alert-danger" role="alert"
							style="display: none;">
							<span class="glyphicon glyphicon-bell"></span>&nbsp;<strong>[주의!]</strong>&nbsp;탈퇴
							시 작성한 일기는 모두 삭제됩니다. 그래도 진행할까요? <br /> <a
								href="/mypet/user/deleteUser?id=${login.id}" class="alert-link">할래요</a>&nbsp;&nbsp;&nbsp;<a
								onclick="$('#deletealert').hide(500)" class="alert-link">안할래요</a>
						</div>
					</div>
					<!-- END MENU -->
				</div>
			</div>

			<div class="col-md-9">
				<div class="profile-content">


					<c:if test="${fn:length(list) < 1}">

						<div>
							<h5>등록된 일기가 없습니다. 일기를 작성해주세요.</h5>

						</div>

					</c:if>

					<!-- 반복문 시작 -->
					<c:forEach items="${list}" var="list">

						<div class="media">

							<div class="col-xs-6 col-md-3">
								<a href="detail?no=${list.no}&id=${list.id}" class="thumbnail">
									<img src="../diaryimage/${list.image}">
								</a>
							</div>

							<div class="media-body">
								<a href="detail?no=${list.no}&id=${list.id}">
									<h3>
										<strong>${list.title}</strong>
									</h3>

								</a> <em> Posted by <span class="text-primary">${list.id}</span>&nbsp;&nbsp;작성일
									${list.regdate}
								</em>
							</div>
						</div>
						<hr>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>




	<!-- script -->
	<script>
		// 프로필 수정 버튼 클릭 
		function goEditProfile() {

			$('#edit_profile').show(1000);
			$('#main_profile').hide(1000);

		}

		// 수정 완료 버튼 클릭
		function goProfile() {

			$('#edit_profile').hide(1000);
			$('#main_profile').show(1000);

		}

		// 프로필 선택한 이미지로 미리보기 구현 
		$(function() {
			// id가 imgInp인 객체의 내용이 변경되면 함수를 수행(input type="file" 인 항목)
			$('#inputimg').on('change', function() {
				// 파일 객체를 가지고 함수를 호출
				readURL(this);
			});
		});

		function readURL(input) {

			if (input.files && input.files[0]) {

				// 파일의 이름 가져오기
				var filename = input.files[0].name;
				var filesize = input.files[0].size;
				// alert(filesize);
				// 가장 뒤의 3자 가져오기 - 
				var ext = filename.substr(filename.length - 3, filename.length);
				// 그림 파일의 확장자인지 확인

				if (ext.toLowerCase() == 'jpg' || ext.toLowerCase() == 'gif'
						|| ext.toLowerCase() == 'png') {

					if (filesize < 10485760) {
						// 그림파일의 확장자가 맞으면 파일의 내용을 읽어서 id가 image인 img 태그에 출력
						var reader = new FileReader();
						// 파일을 다 읽으면 호출되는 함수 설정 
						reader.onload = function(e) {
							$('#inputprofile').attr('src', e.target.result);

						}

					} else {

						alert("10MB 미만의 파일만 등록 가능합니다.");
						return;

					}
					// 선택한 파일을 읽기

					reader.readAsDataURL(input.files[0]);

				} else {
					// 확장자가 이미지가 아님 
					alert("jpg, png 또는 gif 파일만 등록 가능합니다.");
					return;
				}
			}
		}

		// JSON 데이터 출력 
		$(function() {
			// 페이지 로딩 준비 되면 실행되는 함수 
			$(document).ready(
					function() {
						var addr = '/mypet/user/profile';
						var id = $('#id').val();
						// alert(id);
						$.ajax({
							url : addr,
							data : {
								'id' : id
							},
							dataType : 'json',
							success : function(data) {
								// profile 키에 저장된 값 가져오기 
								var user = data.profile;
								// user.id 이런식으로 각각의 값 가져오면 됨 
								// "profile":null,"nickname":"강남이언니", "intro" : "소개"
								// 닉네임 참에 닉네임 출력 
								$('#nick').html(user.nickname);
								$('#inputnick').val(user.nickname);

								// 소개 출력
								if (user.intro == null) {
									// 소개 미 입력 시, 자기 소개를 등록해주세요 
									$('#intro').html('소개를 등록해주세요.');

								} else {
									$('#intro').html(user.intro);
									$('#inputintro').val(user.intro);
								}

								// 사진 출력
								if (user.profile != null) {
									$('#profile').attr('src',
											'../userimage/' + user.profile);
									$('#inputprofile').attr('src',
											'../userimage/' + user.profile);
									$('#hiddenimg').val(user.profile);
								}

							}

						});

					});

		});
	</script>



	<%@include file="../include/footer.jsp"%>
</body>

</html>

