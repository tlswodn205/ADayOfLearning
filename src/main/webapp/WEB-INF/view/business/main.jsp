<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/business/layout/header.jsp" %>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script src="/js/business/salesStatus.js"></script>

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
			<div class="mainNarrowComponent">
				<div class="mainComponentTop">
					<span class="componentTitle">내 정보</span>
					<a class="showMore" href="/business/userDetail"><i class="fa-solid fa-plus"></i> 더보기</a>
				</div>
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
			<div class="mainLectureComponent">
				<div class="mainComponentTop">
					<span class="componentTitle">수업 리스트</span>
					<a class="showMore" href="/business/lectureList"><i class="fa-solid fa-plus"></i> 더보기</a>
				</div>
				<table>
					<thead>		
						<tr>
							<th>오늘 클래스</th>
							<th>신청 인원</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${countTodayLecture}</td>
							<td>${countTodayUser}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>