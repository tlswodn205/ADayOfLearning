<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/business/layout/header.jsp" %>
<main>
	<div class="mainTop">
		<div class="title">내 정보보기</div>
	</div>
	<div id="userDetail" class="mainColumn">
		<form action="/admin/agreeBusiness" id="agreeBusiness" method="post">
			<div class="DetailColumn">
				<table>	
					<tr>
						<td>아이디</td>
						<td><span>${customerUserData.username}</span></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><span>${customerUserData.name}</span></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><span>${customerUserData.address}</span></td>
					</tr>
					<tr>
						<td>상세주소</td>
						<td><span>${customerUserData.addressDetail}</span></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><span>${customerUserData.email}</span></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><span>${customerUserData.phoneNumber}</span></td>
					</tr>
					<tr>
						<td>생년월일</td>
						<td><img src="${customerUserData.birthday}"></td>
					</tr>
				</table>
				
				<input type="button" class="inputBtn" id="agreeBusinessBtn" value="판매자 승인">
			</div>
		</form>
	</div>
</main>

<script>

let AgreeBusiness = {
    version: 1,
    init: function() {
    	$(document).on("click", "#agreeBusinessBtn", ()=>{
    		this.submit();
    	});
    },
    submit:function(){
    	let isChanged = confirm("판매자를 승인하시겠습니까?");
    	if(isChanged){
    		$("#agreeBusiness").submit();
    	}
    },
    
    
};

AgreeBusiness.init();

</script>

<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>