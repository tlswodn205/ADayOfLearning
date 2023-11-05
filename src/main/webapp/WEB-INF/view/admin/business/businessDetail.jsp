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
						<td><span>${businessUserData.username}</span></td>
					</tr>
					<tr>
						<td>상호명</td>
						<td><span>${businessUserData.businessName}</span></td>
					</tr>
					<tr>
						<td>대표자명</td>
						<td><span>${businessUserData.CEOname}</span></td>
					</tr>
					<tr>
						<td>사업장 주소</td>
						<td><span>${businessUserData.businessAddress}${businessUserData.businessAddressDetail}</span></td>
					</tr>
					<tr>
						<td>사업장 전화번호</td>
						<td><span>${businessUserData.businessNumber}</span></td>
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