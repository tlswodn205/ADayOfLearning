<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<main>
	<div id="body">
		<form action="/user/showId" method="post">
			<c:if test="${!showUsernameResponseDto.kakaoLogin}">
				<div>
					<div id="title">아이디 확인</div>
					<div id="userId" name="userId">${showUsernameResponseDto.username}</div>
					<a href="/user/signUp">로그인</a>
					<a href="/user/findPassword">비밀번호 찾기</a>
				</div>
			</c:if>
			<c:if test="${showUsernameResponseDto.kakaoLogin}">
				<div>
					<div id="title">카카오 아이디로 로그인하기</div>
					<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=ccdeb94ef92aa4067769d9ed3712c815&redirect_uri=http://localhost:8080/user/kakao/callback">
					<img src="/images/kakaoButton/kakao_login_large_wide.png" width="400px" alt=""></a>
				</div>
			</c:if>
		</form>
	</div>
	</main>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>