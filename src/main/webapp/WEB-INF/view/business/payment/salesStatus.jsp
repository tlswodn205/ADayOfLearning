<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script src="/js/business/salesStatus.js"></script>

<style>
.chart {
	display: flex;
	justify-content: space-around;
	margin-bottom: 30px;
}
.chart div {
	border: 1px solid #d3d3d3;
	border-radius: 13px;
	text-align: center;
	width: 24%;
}
.chartItem {
	cursor: pointer;
}
</style>

<%@ include file="/WEB-INF/view/business/layout/header.jsp"%>

<main>
	<div class="mainTop">
		<div class="title">매출 관리</div>
	</div>
	<div class="mainColumn">
		<div class="ListColumn">
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
				<!--차트가 그려질 부분-->
				<!-- <canvas id="chart"></canvas> -->
			</div>
			
		<table>
				<thead>
					<tr>
						<th>강의일</th>
						<th>클래스명</th>
						<th>가격</th>
						<th>카테고리</th>
						<th>신청 인원</th>
						<th>상태</th>
						<th>등록일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="lectureList" items="${listPagingResponseDto.list}">
						<tr>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lectureList.sessionDate}" /></td>
							<td><a
								href="/business/lectureDetail/${lectureList.lectureSessionId}">${lectureList.title}</a></td>
							<td>${lectureList.price}</td>
							<td>${lectureList.categoryName}</td>
							<td>${lectureList.student}/${lectureList.maximum}</td>
							<td>${lectureList.state}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lectureList.createdAt}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="d-flex justify-content-center">
				<ul class="pagination">
					<c:if test="${!listPagingResponseDto.first}">
						<li class='page-item'><a class="page-link"
							href="?page=${listPagingResponseDto.currentPage-1}${empty listPagingResponseDto.keyword ? "
							": "&keyword="+= listPagingResponseDto.keyword}${empty listPagingResponseDto.type ? "
							": "&type="+= listPagingResponseDto.type}${empty listPagingResponseDto.status ? "
							": "&status="+= listPagingResponseDto.status}">Prev</a></li>
					</c:if>

					<c:forEach var="num" begin="${listPagingResponseDto.startPageNum}"
						end="${listPagingResponseDto.lastPageNum}">
						<c:choose>
							<c:when test="${num==listPagingResponseDto.currentPage}">
								<li class='page-item'><label>${num}</label></li>
							</c:when>
							<c:otherwise>
								<li class='page-item'><a class='page-link'
									href="?page=${num}${empty listPagingResponseDto.keyword ? "
									": "&keyword="+= listPagingResponseDto.keyword}${empty listPagingResponseDto.type ? "
									": "&type="+= listPagingResponseDto.type}${empty listPagingResponseDto.status ? "
									": "&status="+= listPagingResponseDto.status}">${num}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${!listPagingResponseDto.last}">
						<li class='page-item'><a class="page-link"
							href="?page=${listPagingResponseDto.currentPage+1}${empty listPagingResponseDto.keyword ? "
							": "&keyword="+= listPagingResponseDto.keyword}${empty listPagingResponseDto.type ? "
							": "&type="+= listPagingResponseDto.type}${empty listPagingResponseDto.status ? "
							": "&status="+= listPagingResponseDto.status}">Next</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	
</main>
<script>
	
</script>
<%@ include file="/WEB-INF/view/business/layout/footer.jsp"%>