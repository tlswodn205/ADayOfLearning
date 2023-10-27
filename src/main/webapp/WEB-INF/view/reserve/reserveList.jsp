<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<style>
html, body {
	height: 100%;
	width: 1200px;
	margin: 0 auto;
}
form {
	overflow: hidden;
}
#reserveList .content {
	/* display: flex; */
	/* justify-content: space-between; */
	margin-top: 50px;
}
#reserveList .listBox {
	border: 1px solid #d3d3d3;
	padding: 20px;
}
#reserveList .listItem {
	display: flex;
}
.listItem img {
	margin-right: 30px;
	border-radius: 7px;
}
.listContent {
	display: flex;
	flex-direction: column;
	justify-content: space-around;
}
.listContent h4 {
	margin: 0;
}
.listContent p {
	margin: 0;
}
</style>

<main>
	<div id="reserveList">
		<div class="title">
			<h1>예약 내역</h1>
		</div>
		<div class="content">
			<div class="listBox">
				<div>
					<h3>결제 완료</h3>
				</div>
				<div class="listItem">
					<img alt="" src="https://picsum.photos/120/120">
					<div class="listContent">
						<span>2023.11.02 결제</span>
						<h4>클래스명</h4>
						<p>11월 7일 (화요일) 11시 00분</p>
						<strong>40,000원</strong>
					</div>
				</div>
			</div>
			

			<c:choose>
				<c:when test="${reserveList != null}">
					<c:forEach var="list" items="reserveList">

					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="col">
						<p>예약 내역이 없습니다.</p>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>