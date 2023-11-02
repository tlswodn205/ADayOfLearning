<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<main>
	<div id="body" class="mainColumn">
		<div id="title">아이디 찾기</div>
		<div id="subTitle">
			<p>
				이메일을 입력해주세요
			</p>
		</div>
		<form action="/user/findUsername" method="post" id="postFindUsername">
			<div>
				<input type="email" placeholder="이메일을 입력하세요" id="email"
					name="email">
			</div>
			<input type="button" id= "findUsername" value="아이디 찾기">
		</form>
	</div>
	</main>
	
<script>

$("#findUsername").on("click", function(){
	let email= $("#email").val();
	
	if(!email){
		alert("이메일을 입력해주세요.");
		return false;
	}
	
	$("#postFindUsername").submit();
	
});

</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>