<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<link rel="stylesheet" href="/css/userPage.css">

<main>
	<div id="userPage" class="mainColumn">
		<div id="title">비밀번호 찾기</div>
		<div id="subTitle">
			<p>아이디와 이메일을 입력해주세요</p>
		</div>
		<form action="/user/showPassword" method="post">
			<div class="wideDiv">
				<i class="fa-solid fa-user"></i>
				<input type="text" placeholder="아이디*" id="username" name="username">
			</div>
			<br>
			<div class="wideDiv">
				<i class="fa-solid fa-envelope"></i>
				<input type="email" placeholder="email*" id="email" name="email">
			</div>
			<br>
			<input type="button" class="submitBtn" id="findPassword" value="비밀번호 찾기">
		</form>
	</div>
</main>
<script>

let findPassword = {
    version: 1,
    init: function() {
    	$("#findPassword").on("click", ()=>{
    		this.submit();
    	});
    },
	    
	submit: function(){
		let email= $("#email").val();
		let username= $("#username").val();
	
		$("#findPassword").attr("disabled", true);
		
		if(!username){
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		if(!email){
			alert("이메일을 입력해주세요.");
			return false;
		}
		
		let URL = "/user/showPassword?email="+email+"&username="+username;
		
		fetch(URL, {
		    method: "get",
		}).then(response => response.json())
		  .then(function(jsonData){
			   	if(jsonData){
			   		alert("이메일 전송 완료했습니다.");
			   		window.location.href="/user/signIn"
			   		
			   	}else{
			   		alert("존재하지 않는 계정입니다.");
			   		$("#findPassword").attr("disabled", false);
			   	}
		  });
	
	
	}
}

findPassword.init();
</script>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>