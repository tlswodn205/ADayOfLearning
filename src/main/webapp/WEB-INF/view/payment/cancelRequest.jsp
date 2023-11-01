<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<link rel="stylesheet" href="/css/cancelRequest.css">

<script type="text/javascript">
	function reqCancel() {
		document.cancelForm.submit();
	}
</script>
<main>
	<div id="cancelRequest">
		<div class="title">
			<h1>예약 취소 요청</h1>
		</div>

		<div class="content">
			<div class="contentInfo">
				<h4>취소 사유를 입력해주세요.</h4>
			</div>

			<form name="cancelForm" method="post" target="_self" action="/payment/cancelResult">
				<input type="hidden" name="paymentId" value="${payment.paymentId}">
				<table>
					<tr>
						<th>원거래 ID</th>
						<td><input type="text" name="TID" value="${payment.tid}" /></td>
					</tr>
					<tr>
						<th>취소 금액</th>
						<td><input type="text" name="CancelAmt"
							value="${payment.amount}" /></td>
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

		</div>


	</div>


</main>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>