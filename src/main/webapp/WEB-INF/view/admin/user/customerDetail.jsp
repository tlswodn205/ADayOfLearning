<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/admin/layout/header.jsp" %>
<main>
	<div class="mainTop">
		<div class="title">구매자 상세보기</div>
	</div>
	<div id="userDetail" class="mainColumn">
		<form action="/admin/updateUser/${customerUserData.userId}" id="updateUser" method="post">
			<div class="DetailColumn">
				<input type="hidden" id="userId" value="${customerUserData.userId}">
				<table>	
					<tr>
						<td>아이디</td>
						<td><span>${customerUserData.username}</span></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="text" name="password"></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="name" value="${customerUserData.name}"></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><input type="text" name="address" value="${customerUserData.address}"></td>
					</tr>
					<tr>
						<td>상세주소</td>
						<td><input type="text" name="addressDetail" value="${customerUserData.addressDetail}"></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" name="email" value="${customerUserData.email}"></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" name="phoneNumber" value="${customerUserData.phoneNumber}"></td>
					</tr>
					<tr>
						<td>생년월일</td>
						<td><input type="date" name="birthday" value="${customerUserData.birthday}"></td>
					</tr>
				</table>
				<div class="detailColumnBottom">
					<input type="button" class="updateBtn" id="userUpdateBtn" value="유저 데이터 수정">
					<input type="button" class="deleteBtn" id="userDeleteBtn" value="유저 삭제">
				</div>
			</div>
		</form>
	</div>
</main>

<script>

let adminCustomerDetail = {
    version: 1,
    init: function() {
    	$(document).on("click", "#userUpdateBtn", ()=>{
    		this.update();
    	});
    	$(document).on("click", "#userDeleteBtn", ()=>{
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

    	if(!isDelete){
    		return false;
    	}
    	
    	let realDelete = confirm("정말 유저 정보를 삭제하시겠습니까?");
    	

    	
    	if(realDelete){
        	let userID = $("#userId").val();
        	let URL = "/admin/deleteUser/"+userID;
    		fetch(URL, {
    		    method: "delete", 
    		}).then(response => response.json())
    		  .then(function(jsonData){
    	       	if(jsonData==1){
    			  	alert("삭제 완료했습니다.");
    			}
    		}).catch(error => {
	      		 alert("삭제 실패했습니다.");	
	  			});
    	}
    },
};

adminCustomerDetail.init();

</script>

<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>