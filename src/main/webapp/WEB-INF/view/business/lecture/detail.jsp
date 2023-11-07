<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/business/layout/header.jsp" %>
<link rel="stylesheet" href="/css/business/lectureDetail.css">

<main>
	<div class="mainTop">
		<div class="title">강의 신청자 목록</div>
	</div>
	<div id="lectureDetail" class="mainColumn">
		<div class="lectureTable">
			<c:choose>
				<c:when test="${lecture != null}">
					<table>
						<tr>
							<th>번호</th>
							<th>신청자명</th>
							<th>전화번호</th>
							<th>이메일</th>
							<th>가격</th>
							<th>결제 상태</th>
							<th>결제일</th>
							<th>취소일</th>
							<th>취소 승인</th>
						</tr>
						<c:forEach var="list" items="${lecture}" varStatus="status">
						
							<tr>
								<td><c:out value="${status.count}"></c:out></td>
								<td><a href="/business/reserveDetail/${list.reserveId}">${list.name}</a></td>
								<td>${list.phoneNumber}</td>
								<td>${list.email}</td>
								<td>${list.amount}</td>
								<td>${list.state}</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${list.paymentDate}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${list.refundDate}" /></td>
								<td>
									<c:if test="${list.state eq '취소 완료'}">
										<p>완료</p>
									</c:if>
								</td>
							</tr>
							
						</c:forEach>
					</table>
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
<script>
/* let refundInit = {
		version: 1,
		init: function() {
			$(document).("click", "#refundBtn", () => {
				
			});
		}
		
		refundSubmit:function() {
			window.location.href = '/business/';
		}
} */

</script>

<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>