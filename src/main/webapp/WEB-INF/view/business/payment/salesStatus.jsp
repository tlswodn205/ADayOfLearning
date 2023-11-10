<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script src="/js/business/salesStatus.js"></script>

<style>
.table {
	display: flex;
	
}
.tableItem {
	border: 1px solid #d3d3d3;
	border-radius: 13px;
	text-align: center;
	cursor: pointer;
	width: 20%;
	margin-right: 20px;
}
.chartArea {
	margin: 20px;
}
</style>

<%@ include file="/WEB-INF/view/business/layout/header.jsp"%>

<main>
	<div class="mainTop">
		<div class="title">매출 관리</div>
	</div>
	<div class="mainColumn">
		<div class="DetailColumn">
			<div class="table">
				<div id="sevenDaysTable" class="tableItem">
					<h4>최근 매출</h4>
					<p>${sevenDaysTotal}</p>
				</div>
				<div id="monthlyTable" class="tableItem">
					<h4>연 매출</h4>
					<p>${monthlyTotal}</p>
				</div>
				<div class="tableItem">
					<h4>지난 달 매출</h4>
					<p>${lastMonthTotal}</p>
				</div>
			</div>
			<div id="chartArea" class="chartArea">
				<!--차트가 그려질 부분-->
				<!-- <canvas id="chart"></canvas> -->
			</div>
		</div>
	</div>
</main>
<script>
	
</script>
<%@ include file="/WEB-INF/view/business/layout/footer.jsp"%>