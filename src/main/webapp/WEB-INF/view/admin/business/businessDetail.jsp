<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/admin/layout/header.jsp" %>
<main>
	<div class="mainTop">
		<div class="title">내 정보보기</div>
	</div>
	<div id="businessDetail" class="mainColumn">
		<form action="/admin/updateBusiness" id="businessDetailForm" method="post">
			<div class="DetailColumn">
				<input type="hidden" id="userId" name="userId" value="${businessUserData.userId}">
				<table>	
					<tr>
						<td>아이디</td>
						<td><span>${businessUserData.username}</span></td>
					</tr>
					<c:if test="${businessUserData.kakao}">
						<tr>
							<td>비밀번호</td>
							<td><input type="text" name="password"></td>
						</tr>
					</c:if>
					<tr>
						<td>상호명</td>
						<td><input type="text" name="businessName" value="${businessUserData.businessName}"></td>
					</tr>
					<tr>
						<td>대표자명</td>
						<td><input type="text" name="CEOname" value="${businessUserData.CEOname}"></td>
					</tr>
					<tr>
						<td>사업장 주소</td>
						<td><input type="text" name="businessAddress" value="${businessUserData.businessAddress}"></td>
					</tr>
					<tr>
						<td>사업장 상세주소</td>
						<td><input type="text" name="businessAddressDetail" value="${businessUserData.businessAddressDetail}"></td>
					</tr>
					<tr>
						<td>사업장 전화번호</td>
						<td><input type="text" name="businessNumber" value="${businessUserData.businessNumber}"></td>
					</tr>
					<tr>
						<td>사업자 등록번호</td>
						<td><span>${businessUserData.businessRegistrationNumber}</span></td>
					</tr>
					<tr>
						<td>사업자 등록증</td>
						<td><img src="${businessUserData.businessRegistrationImg}"></td>
					</tr>
				</table>
				<div class="detailColumnBottom">
					<input type="button" class="inputBtn" id="businessUpdateBtn" value="판매자 수정">
					<input type="button" class="deleteBtn" id="businessDeleteBtn" value="판매자 삭제">
				</div>
			</div>
		</form>
	</div>
</main>

<script>

let businessDetail = {
    version: 1,
    init: function() {
    	$(document).on("click", "#businessUpdateBtn", ()=>{
    		this.update();
    	});
    	$(document).on("click", "#businessDeleteBtn", ()=>{
    		this.update();
    	});
    },
    update:function(){
    	let isChanged = confirm("판매자를 수정하시겠습니까?");
    	if(isChanged){
    		$("#businessDetailForm").submit();
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
        	let URL = "/admin/deleteBusiness/"+userID;
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
    }
    
    
};

businessDetail.init();

</script>

<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>