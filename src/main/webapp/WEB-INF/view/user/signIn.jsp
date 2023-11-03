<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<main>
	<div id="signIn" class="mainColumn">
		<form action="/user/signIn" method="post" id="signInForm">
			<input type="text" id="username" name="username" placeholder="아이디를 입력하세요.">
			<br>
			<input type="password" id="password" name="password" placeholder="패스워드를 입력하세요.">
			<br>
			<input type="button" id="signInBtn" value="로그인">
		</form>
	</div>
	<div>
		<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=ccdeb94ef92aa4067769d9ed3712c815&redirect_uri=http://localhost:8080/user/kakao/callback">
		<img src="/images/kakaoButton/kakao_login_large_wide.png" width="400px" alt=""></a>
	</div>
	
		<a href="/user/signUp">회원가입</a>
		<a href="/user/findUsername">아이디 찾기</a>
		<a href="/user/findPassword">비밀번호 찾기</a>
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