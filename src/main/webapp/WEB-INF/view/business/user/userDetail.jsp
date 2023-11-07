<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/business/layout/header.jsp" %>
<main>
	<div class="mainTop">
		<div class="title">내 정보보기</div>
	</div>
	<div id="userDetail" class="mainColumn">
		<form action="/business/businessUpdate" id="businessUpdate" method="post">
			<div class="DetailColumn">
				<table>	
					<tr>
						<td>아이디</td>
						<td><span>${businessUserData.username}</span></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="text" name="password"></td>
					</tr>
					<tr>
						<td>상호명</td>
						<td><input type="text" name="businessName" id="businessName" value="${businessUserData.businessName}"></td>
					</tr>
					<tr>
						<td>대표자명</td>
						<td><input type="text" name="CEOname" id="CEOname" value="${businessUserData.CEOname}"></td>
					</tr>
					<tr>
						<td>사업장 주소</td>
						<td><input type="text" name="businessAddress" id="businessAddress" value="${businessUserData.businessAddress}"></td>
					</tr>
					<tr>
						<td>사업장 상세주소</td>
						<td><input type="text" name="businessAddressDetail" id="businessAddressDetail" value="${businessUserData.businessAddressDetail}"></td>
					</tr>
					<tr>
						<td>사업장 전화번호</td>
						<td><input type="text" name="businessNumber" id="businessNumber" value="${businessUserData.businessNumber}"></td>
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
				
				<input type="button" class="inputBtn" id="businessUpdateBtn" value="정보변경">
			</div>
		</form>
	</div>
</main>

<script>

let BusinessUserDetail = {
    version: 1,
    init: function() {
    	$(document).on("click", "#businessUpdateBtn", ()=>{
    		this.update();
    	});
    },
    update:function(){
    	let isChanged = confirm("정보를 변경하시겠습니까?");
    	if(isChanged){
    		$("#businessUpdate").submit();
    	}
    },
    
    
};

BusinessUserDetail.init();

</script>

<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>