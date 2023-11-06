<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/business/layout/header.jsp" %>
<link rel="stylesheet" href="/css/business/lectureDetail.css">

<main>
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
							<th>취소 사유</th>
							<th>취소일</th>
							<th>승인</th>
						</tr>
						<c:forEach var="item" items="${lecture}" varStatus="status">
						
							<tr>
								<td><c:out value="${status.count}"></c:out></td>
								<td>${item.name}</td>
								<td>${item.phoneNumber}</td>
								<td>${item.email}</td>
								<td>${item.amount}</td>
								<td>${item.state}</td>
								<td>${item.refundInfo}</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${item.refundDate}" /></td>
								<td>
									<c:if test="${item.state eq '취소 요청'}">
										<button type="button" class="refundBtn">확인</button>
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
<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>