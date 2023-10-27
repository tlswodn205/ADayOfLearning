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
</main>

<script>
$(document).on("click", "#signInBtn", function(){
	
	
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
});
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>