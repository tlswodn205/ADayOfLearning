<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/business/layout/header.jsp" %>
<main>
	<div class="mainTop">
		<div class="title">하루의 배움</div>
	</div>
	<div id="example" class="mainColumn">
		<div class="mainRow">
			<div class="mainUserComponent">
				<a class="showMore" href="/business/userDetail"><i class="fa-solid fa-plus"></i> 더보기</a>
				<table>
					<thead>		
						<tr>
							<th>아이디</th>
							<th>이메일</th>
							<th>전화번호</th>
							<th>사업자 등록번호</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${userData.username}</td>
							<td>${userData.email}</td>
							<td>${userData.phoneNumber}</td>
							<td>${userData.businessRegistrationNumber}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>