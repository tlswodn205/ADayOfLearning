<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/business/layout/header.jsp" %>
<main>
	<div class="mainTop">
		<div class="title">하루의 배움</div>
	</div>
	<div id="example" class="mainColumn">
		<div class="mainRow">
			<div class="mainNarrowComponent">
				<a class="showMore" href="/admin/userDetail"><i class="fa-solid fa-plus"></i> 더보기</a>
				<table>
					<thead>		
						<tr>
							<th>아이디</th>
							<th>이름</th>
							<th>이메일</th>
							<th>전화번호</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="customer" items="${adminMainResponseDto.adminMainCustomerListResponseDto}">
							<tr>
								<td>${customer.username}</td>
								<td>${customer.name}</td>
								<td>${customer.email}</td>
								<td>${customer.phoneNumber}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="mainNarrowComponent">
				<a class="showMore" href="/admin/userDetail"><i class="fa-solid fa-plus"></i> 더보기</a>
				<table>
					<thead>		
						<tr>
							<th>상호명</th>
							<th>사업장 전화번화</th>
							<th>사업자 등록번호</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="business" items="${adminMainResponseDto.adminMainBusinessListResponseDto}">
							<tr>
								<td>${business.BusinessName}</td>
								<td>${business.BusinessNumber}</td>
								<td>${business.businessRegistrationNumber}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="mainRow">
			<div class="mainNarrowComponent">
				<a class="showMore" href="/admin/userDetail"><i class="fa-solid fa-plus"></i> 더보기</a>
				<table>
					<thead>		
						<tr>
							<th>아이디</th>
							<th>이름</th>
							<th>이메일</th>
							<th>전화번호</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="customer" items="${adminMainResponseDto.adminMainCustomerListResponseDto}">
							<tr>
								<td>${customer.username}</td>
								<td>${customer.name}</td>
								<td>${customer.email}</td>
								<td>${customer.phoneNumber}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>