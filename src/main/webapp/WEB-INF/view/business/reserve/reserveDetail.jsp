<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/business/layout/header.jsp" %>

<script type="text/javascript">
	function reqCancel() {
		if(confirm("환불 승인하시겠습니까?")) {
			document.cancelForm.submit();
		}
	}
</script>
<main>
	<div class="mainTop">
		<div class="title">예약 및 결제 확인</div>
	</div>
	
	<div id="reserveDetail" class="mainColumn">
		<div class="DetailColumn">
			<div class="content">
				<table>	
					<tr>
						<td>클래스명</td>
						<td><span>${reserve.title}</span></td>
					</tr>
					<tr>
						<td>강의일</td>
						<td><span>${reserve.sessionDate}</span></td>
					</tr>
					<tr>
						<td>가격</td>
						<td><span>${reserve.amount}</span></td>
					</tr>
					<tr>
						<td>신청자명</td>
						<td><span>${reserve.name}</span></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><span>${reserve.phoneNumber}</span></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><span>${reserve.email}</span></td>
					</tr>
					<tr>
						<td>결제 상태</td>
						<td><span>${reserve.state}</span></td>
					</tr>
					<tr>
						<td>결제 수단</td>
						<td><span>${reserve.paymentKind}</span></td>
					</tr>
					<tr>
						<td>결제일</td>
						<td><span>${reserve.paymentDate}</span></td>
					</tr>
				
					<c:if test="${reserve.state == '취소 요청'}">
						<tr>
							<td>취소 사유</td>
							<td><span>${reserve.refundInfo}</span></td>
						</tr>
						<form name="cancelForm" method="post" target="_self" action="/business/cancelResult">
							<input type="hidden" name="paymentId" value="${reserve.paymentId}">
							<table>
								<tr>
									<th>원거래 ID</th>
									<td><input type="text" readonly name="TID" value="${reserve.tid}" />${payment.tid}</td>
								</tr>
								<tr>
									<th>취소 금액</th>
									<td><input type="text" readonly name="CancelAmt" value="${reserve.amount}" /></td>
								</tr>
								<tr>
									<th>부분취소 여부</th>
									<td>
										<input type="radio" name="PartialCancelCode" value="0" checked="checked" /> 전체취소
										<input type="radio"	name="PartialCancelCode" value="1" /> 부분취소
									</td>
								</tr>
							</table>
							<a href="#" onClick="reqCancel();">요 청</a>
						</form>
					</c:if>
				</table>
			</div>
		</div>
	</div>
</main>

<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>