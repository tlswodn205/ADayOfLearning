<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>

 <link rel="stylesheet" href="/css/signIn.css">
  
<main>
	<div id="signIn" class="mainColumn">
		<form action="/user/signIn" method="post" id="signInForm">
			<div class="usernameInput">
				<i class="fa-solid fa-user"></i>
				<input type="text" id="username" name="username" placeholder="아이디를 입력하세요.">
			</div>
			<br>
			<div>
				<i class="fa-solid fa-lock"></i>
				<input type="password" id="password" name="password" placeholder="패스워드를 입력하세요.">
			</div>
			<br>
			<input type="button" class="signInBtn" id="signInBtn" value="로그인">
		</form>
		<br>
	<div>
		<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=ccdeb94ef92aa4067769d9ed3712c815&redirect_uri=http://localhost:8080/user/kakao/callback">
			<img src="/images/kakaoButton/kakao_login_large_wide.png" id="kakaoBtn" alt="">
		</a>
	</div>
	<div class="notSignIn">
		아직 하루의 배움 회원이 아니신가요? <a href="/user/signUp">회원가입</a>
		<br>
		<br>
		<a href="/user/findUsername">아이디 찾기</a> |
		<a href="/user/findPassword">비밀번호 찾기</a>
	</div>
	</div>
</main>

<script>


let signInEvent = {
    version: 1,
    init: function() {
    	$(document).on("click", "#signInBtn", ()=>{
    		this.signIn();
    	});
    },
	signIn : function(){
		let username = $("#username").val();
		let password = $("#password").val();
		
		
		if(!username){
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		if(!password){
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		
		$("#signInForm").submit();
	}
	
};

signInEvent.init();
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>