<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/business/layout/header.jsp" %>
<main>
	<div class="mainTop">
		<div class="title">하루의 배움</div>
	</div>
	<div id="example" class="mainColumn">
		<div class="mainRow">
			<div class="mainNarrowComponent">
				<div class="mainComponentTop">
					<span class="componentTitle">구매자 리스트</span>
					<a class="showMore" href="/admin/customerList"><i class="fa-solid fa-plus"></i> 더보기</a>
				</div>
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
				<div class="mainComponentTop">
					<span class="componentTitle">판매자 리스트</span>
					<a class="showMore" href="/admin/businessList"><i class="fa-solid fa-plus"></i> 더보기</a>
				</div>
				<table>
					<thead>		
						<tr>
							<th>상호명</th>
							<th>사업장 전화번호</th>
							<th>사업자 등록번호</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="business" items="${adminMainResponseDto.adminMainBusinessListResponseDto}">
							<tr>
								<td>${business.businessName}</td>
								<td>${business.businessNumber}</td>
								<td>${business.businessRegistrationNumber}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="mainRow">
			<div class="mainNarrowComponent">
				<div class="mainComponentTop">
					<span class="componentTitle">판매자 신청 리스트</span>
					<a class="showMore" href="/admin/requestBusinessList"><i class="fa-solid fa-plus"></i> 더보기</a>
				</div>
				<table>
					<thead>		
						<tr>
							<th>상호명</th>
							<th>사업장 전화번호</th>
							<th>사업자 등록 번호</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="requestBusiness" items="${adminMainResponseDto.adminMainRequestBusinessListResponseDto}">
							<tr>
								<td>${requestBusiness.businessName}</td>
								<td>${requestBusiness.businessNumber}</td>
								<td>${requestBusiness.businessRegistrationNumber}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>