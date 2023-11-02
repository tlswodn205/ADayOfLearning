<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/business/layout/header.jsp" %>
<main>
	<div class="mainTop">
		<div class="title">하루의 배움</div>
	</div>
	<div id="example" class="mainColumn">
		<div class="DetailColumn">
			<table>	
				<tr>
					<td>아이디</td>
					<td><input type="text" name="username" id="username" readonly value="${businessUserData.username}"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="text" name="password" readonly></td>
				</tr>
				<tr>
					<td>상호명</td>
					<td><input type="text" name="businessName" id="businessName" readonly value="${businessUserData.businessName}"></td>
				</tr>
				<tr>
					<td>대표자명</td>
					<td><input type="text" name="CEOname" id="CEOname" readonly value="${businessUserData.CEOname}"></td>
				</tr>
				<tr>
					<td>사업장 주소</td>
					<td><input type="text" name="businessAddress" id="businessAddress" readonly value="${businessUserData.businessAddress}"></td>
				</tr>
				<tr>
					<td>사업장 상세주소</td>
					<td><input type="text" name="businessAddressDetail" id="businessAddressDetail" readonly value="${businessUserData.businessAddressDetail}"></td>
				</tr>
				<tr>
					<td>사업장 전화번호</td>
					<td><input type="text" name="businessNumber" id="businessNumber" readonly value="${businessUserData.businessNumber}"></td>
				</tr>
				<tr>
					<td>사업자 등록번호</td>
					<td><input type="text" name="businessRegistrationNumber" readonly value="${businessUserData.businessRegistrationNumber}"></td>
				</tr>
				<tr>
					<td>사업자 등록증</td>
					<td><img src="${businessUserData.businessRegistrationImg}"></td>
				</tr>
			</table>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>