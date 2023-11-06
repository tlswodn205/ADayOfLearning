<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/admin/layout/header.jsp" %>
<main>
	<div class="mainTop">
		<div class="title">내 정보보기</div>
	</div>
	<div id="userDetail" class="mainColumn">
		<form action="/admin/updateUser" id=updateUser" method="post">
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
						<td><span>${customerUserData.birthday}</span></td>
					</tr>
				</table>
				<input type="button" class="updateBtn" id="userUpdateBtn" value="유저 데이터 수정">
				<input type="button" class="deleteBtn" id="userDeleteBtn" value="유저 데이터 수정">
			</div>
		</form>
	</div>
</main>

<script>

let AgreeBusiness = {
    version: 1,
    init: function() {
    	$(document).on("click", "#updateUserBtn", ()=>{
    		this.update();
    	});
    	$(document).on("click", "#deleteUserBtn", ()=>{
    		this.delete();
    	});
    },
    update:function(){
    	let isChanged = confirm("유저 정보를 수정하시겠습니까?");
    	if(isChanged){
    		$("#updateUser").submit();
    	}
    },

    delete:function(){
    	let isDelete = confirm("유저 정보를 삭제하시겠습니까?");
    	if(isDelete){
    		
    	}
    },
    
    
};

AgreeBusiness.init();

</script>

<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>