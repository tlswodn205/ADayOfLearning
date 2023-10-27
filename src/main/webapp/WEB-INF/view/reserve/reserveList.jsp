<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<link rel="stylesheet" href="/css/reserveList.css">

<main>
	<div id="reserveList">
		<div class="title">
			<h1>예약 내역</h1>
		</div>
		<div class="content">

			<c:choose>
				<c:when test="${reserveList != null}">
					<c:forEach var="list" items="${reserveList}">
						<div class="listBox">
							<a href="/reserve/detail/${list.reserveId}">
								<div>
									<h3>${list.state}</h3>
								</div>
								<div class="listItem">
									<img alt="" src="https://picsum.photos/120/120">
									<div class="listContent">
										<span>${list.paymentDate}</span>
										<h4>${list.title}</h4>
										<p>${list.sessionDate}</p>
										<strong>${list.amount}</strong>
									</div>
								</div>
							</a>
						</div>
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