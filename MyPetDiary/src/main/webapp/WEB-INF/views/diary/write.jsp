<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Pet Diary</title>
</head>
<body>
	<!-- 상단 네비 -->
	<%@include file="../include/header.jsp"%>

	<script>
		// 현재 페이지가 로드되고 난 후 function을 수행
		// 스트립트 내에서 $가 들어가는 문장은 모두 JQuery 문
		$(function() {
			// id가 imgInp인 객체의 내용이 변경되면 함수를 수행(input type="file" 인 항목)
			$('#imgInp').on('change', function() {
				// 파일 객체를 가지고 함수를 호출
				readURL(this);
			});
		});
		// 위에서 사용한 자바 스크립트 함수 만들기 -> 자바 스크립트 문
		// input 이름은 달라져도 됨 input 위에서 this
		function readURL(input) {
			// 파일을 선택 했다면
			if (input.files && input.files[0]) {
				// 파일의 이름 가져오기
				var filename = input.files[0].name;
				var filesize = input.files[0].size;
				// alert(filesize);
				// 가장 뒤의 3자 가져오기 - 
				var ext = filename.substr(filename.length - 3, filename.length);
				// 그림 파일의 확장자인지 확인

				/* if (filesize < 10485760) {
					alert("10MB 미만의 파일만 등록 가능합니다.");
					return;

				} else if (ext.toLowerCase() != 'jpg'
						|| ext.toLowerCase() != 'gif'
						|| ext.toLowerCase() != 'png') {

					alert("jpg, png 또는 gif 파일만 등록 가능합니다.");
					return;

				} else {

					// 그림파일의 확장자가 맞으면 파일의 내용을 읽어서 id가 image인 img 태그에 출력
					var reader = new FileReader();
					// 파일을 다 읽으면 호출되는 함수 설정 
					reader.onload = function(e) {
						$('#image').attr('src', e.target.result);
						$('#helpmsg').html(''); // 이미지 등록 시 헬프메세지 없애기
					}
					// 선택한 파일을 읽기
					reader.readAsDataURL(input.files[0]);
				} */

				
		if (ext.toLowerCase() == 'jpg' || ext.toLowerCase() == 'gif'
						|| ext.toLowerCase() == 'png') {

					if (filesize < 10485760) {
						// 그림파일의 확장자가 맞으면 파일의 내용을 읽어서 id가 image인 img 태그에 출력
						var reader = new FileReader();
						// 파일을 다 읽으면 호출되는 함수 설정 
						reader.onload = function(e) {
							$('#image').attr('src', e.target.result);
							$('#helpmsg').html(''); // 이미지 등록 시 헬프메세지 없애기
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

		// 스크립트
	</script>



	<!-- 타이틀 -->
	<div class="bs-docs-section">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
				<div class="page-header">
					<h4 id="forms">일기 작성</h4>
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">

				<form method="post" enctype="multipart/form-data">
					<div class="box-body">
						<!-- name의 값은 테이블 이름과 일치 시키기 -->
						<label class="control-label"><strong>제목</strong></label> <input
							type="text" name="title" class="form-control"
							placeholder="오늘 하루는 어떤 하루였나요?" required />
					</div>
					<br />

					<div class="form-group">
						<label class="control-label"><strong>내용</strong></label>
						<textarea class="form-control" name="content" rows="10"
							placeholder="반려동물과 함께한 당신의 하루를 기록하세요." required></textarea>

					</div>
					<br />
					<!-- 이미지 -->
					<!-- 사용자가 파일을 선택하면 그 파일의 섬네일을 보여주기 -->
					<div class="form-group">
						<div class="text-right">
							<div class="well" style="background: white; border: 1px;">
								<br /> <img class="img-responsive center-block" id="image" />
								<br /> <span id="helpmsg" class="help-block"
									style="text-align: center;">일기와 함께 보관할 사진을 넣어주세요. 사진을
									추가하지 않을 경우 기본 이미지가 저장됩니다.</span>
							</div>
							<label class="btn btn-default btn-file"> 사진 선택 <input
								type="file" id="imgInp" name="image" style="display: none;">
							</label>
						</div>
					</div>
					<hr>
					<!-- 공유 설정 체크박스 추가 -->
					<div class="form-group">
						<input type="checkbox" name="ckshare" value="true"
							checked="checked">&nbsp;<span
							class="glyphicon glyphicon-share text-primary"></span>&nbsp;작성한
						일기를 게시판에 공유합니다.

					</div>

					<br />
					<div class="box-footer">

						<div class="text-center">
							<button type="submit" class="btn btn-success">작성완료</button>
							<button type="reset" class="btn btn-warning">작성취소</button>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>

	<!-- 하단 카피  -->
	<%@include file="../include/footer.jsp"%>
</body>
</html>