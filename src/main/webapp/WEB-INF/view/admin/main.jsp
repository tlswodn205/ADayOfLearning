<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/admin/layout/header.jsp" %>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script src="/js/admin/salesStatus.js"></script>
<main>
	<div class="mainTop">
		<div class="title">하루의 배움</div>
	</div>
	<div id="example" class="mainColumn">
	
		<div class="mainRow">
			<div class="mainWideComponent" >
				<div class="mainComponentTop">
					<span class="componentTitle">수익 정보</span>
					<a class="showMore" href="/business/userDetail"><i class="fa-solid fa-plus"></i> 더보기</a>
				</div>
				<div class="chart">
					<div id="sevenDaysTable" class="chartItem">
						<h4>최근 매출</h4>
						<p><fmt:formatNumber value="${sevenDaysTotal}" groupingUsed="true"/>원</p>
					</div>
					<div id="monthlyTable" class="chartItem">
						<h4>연 매출</h4>
						<p><fmt:formatNumber value="${monthlyTotal}" groupingUsed="true"/>원</p>
					</div>
					<div>
						<h4>지난 달 매출</h4>
						<p><fmt:formatNumber value="${lastMonthTotal}" groupingUsed="true"/>원</p>
					</div>
					<div>
						<h4>다음 7일 예상 매출</h4>
						<p><fmt:formatNumber value="${nextSevenDaysTotal}" groupingUsed="true"/>원</p>
					</div>
				</div>
				<div id="chartArea" class="chartArea">
				</div>
			</div>
		</div>
		<div class="mainRow">
			<div class="mainWideComponent">
				<div class="mainComponentTop">
					<span class="componentTitle">구매자 리스트</span>
					<a class="showMore" href="/admin/customerList"><i class="fa-solid fa-plus"></i> 더보기</a>
				</div>

				<div class="smallChartComponent">
					<div class="smallChart">
						<div id="customerChartArea"></div>
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
			</div>
		</div>
		
		<div class="mainRow">
			<div class="mainWideComponent">
				<div class="mainComponentTop">
					<span class="componentTitle">판매자 리스트</span>
					<a class="showMore" href="/admin/businessList"><i class="fa-solid fa-plus"></i> 더보기</a>
				</div>
				<div class="smallChartComponent">
					<div class="smallChart">
						<div id="businessChartArea"></div>
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
		</div>
		<div class="mainRow">
			<div class="mainWideComponent">
				<div class="mainComponentTop">
					<span class="componentTitle">판매자 신청 리스트</span>
					<a class="showMore" href="/admin/requestBusinessList"><i class="fa-solid fa-plus"></i> 더보기</a>
				</div>
				<div class="smallChartComponent">
					<div class="smallChart">
						<div id="requestBusinessChartArea"></div>
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
	</div>
</main>
<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>