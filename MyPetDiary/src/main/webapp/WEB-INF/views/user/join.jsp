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


		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-10 col-sm-offset-2">
			<div class="panel panel-warning">
				<div class="panel-heading">
					<div class="panel-title">회원 가입</div>

				</div>

				<div style="padding-top: 30px" class="panel-body">

					<!-- 회원가입 폼 -->
					<form class="form-horizontal" id="joinform"
						onsubmit="return check()" method="post">
						<!-- 아이디 중복 검사 여부 저장할 히든 태그 -->
						<input type="hidden" id="idCheck" value="false" />
						<fieldset>
							<div class="form-group">


								<label class="col-lg-2 control-label">아이디</label>
								<div class="col-lg-10">
									<!-- 포커스가 이동할 때 발생시킬 함수 -->
									<input type="text" class="form-control" id="id" name="id"
										onblur="confirmID()" maxlength="30"
										pattern="([a-z,A-Z,0-9]){4,}" required="required"
										placeholder="아이디 - 4자 이상(알파벳, 숫자만 사용)">
									<!-- 아이디 중복 검사 후 결과를 출력할 공간  -->
									<div id="idDiv"></div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-lg-2 control-label">비밀번호</label>
								<div class="col-lg-10">
									<input type="password" class="form-control" id="pw" name="pw"
										placeholder="비밀번호를 입력하세요" required="required" maxlength="30">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-2 control-label">닉네임</label>
								<div class="col-lg-10">
									<input type="text" class=" form-control" id="nickname"
										name="nickname" placeholder="닉네임을 입력하세요(2자 이상)"
										pattern="([a-z,A-Z,0-9,가-힣]){2,}" required="required"
										maxlength="30">
								</div>
							</div>

							<div class="form-group">
								<div class="col-lg-offset-2 col-lg-10">
									<button type="submit" class="btn btn-primary">가입하기</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>


	<script>
		// form에서 서브밋 버튼 클릭 시 실행할 check() 메소드 생성  
		function check() {
			// 아이디가 중복인 상태에서 회원 가입 버튼 누르면 다음 페이지로 넘어갈 수 없도록 
			if ($('#idCheck').val() == 'false') {
				$('#idDiv').html('[아이디 중복]이미 사용 중인 아이디 입니다.');
				$('#idDiv').css('color', 'red');
				return false;

			}
		}

		// 아이디 입력 창에서 포커스가 이동하면 아이디 중복 체크 결과에 따라 메세지를 idDiv에 출력  
		function confirmID() {
			// 1) 요청 주소 저장  
			var addr = '/mypet/user/idCheck';
			// 2) 요청에 넘겨 줄 파라미터 id 값 가져와서 저장
			var id = $('#id').val();
			// 3) ajax로 컨트롤러에서 전달한 JSON 결과 가져오기
			// url  요청 주소
			// data  파라미터 넘길 값 (없으면 생략)
			// dataType 받아오는 결과 값의 타입
			// success 성공 했을 때 실행되는 함수 
			// error 실패 했을 때 실행되는 함수 설정 

			$.ajax({
				url : addr,
				data : {
					'id' : id
				},
				dataType : 'json',
				success : function(data) {
					//alert(data.result);
					if (data.result == true) {
						// 사용가능한 아이디 
						// (1) idDiv에 성공 메세지 출력
						$('#idDiv').html('사용 가능한 아이디 입니다.');
						$('#idDiv').css('color', 'blue');
						// (2) idCheck 히든 태그에 true 저장
						$('#idCheck').val('true');

					} else {
						// 사용할 수 없는 아이디 
						// (1) idDiv에 실패 메세지 출력
						$('#idDiv').html('이미 사용 중인 아이디 입니다.');
						$('#idDiv').css('color', 'red');
						// (2) idCheck 히든 태그에 false 저장
						$('#idCheck').val('false');

					}

				}

			});
		}
	</script>




	<%@include file="../include/footer.jsp"%>
</body>


</html>