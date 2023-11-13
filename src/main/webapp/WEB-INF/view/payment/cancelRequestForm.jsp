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
			<div class="contentInfo line">
				<h3>취소 사유를 입력해주세요.</h3>
			</div>
			<div class="sectionBox line">
				<div class="sectionBox-area">
					<div class="imgContainer">
						<img alt="썸네일" src="${payment.img}">
					</div>
					<div class="classItem">
						<p>${payment.businessName}</p>
						<h4>${payment.title}</h4>
						<p>
							<fmt:formatDate pattern="yyyy년 MM월 dd일 (E요일), HH시 mm분" value="${payment.sessionDate}" />
						</p>
					</div>
				</div>
			</div>
			<div class="sectionBox line">
				<div class="row">
					<p>예약 번호</p>
					<p>${payment.reserveId}</p>
				</div>
				<div class="row">
					<p>결제 일시</p>
					<p><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${payment.createdAt}" />
					</p>
				</div>
				<div class="row">
					<p>결제 금액</p>
					<p>${payment.amount}</p>
				</div>
			</div>
			<div class="refundTitle">
				<h4>상세 사유 입력</h4>
			</div>
			<form method="post" action="/payment/cancelResult" id="cancelForm">
				<input type="hidden" id="reserveId" name="reserveId" value="${payment.reserveId}">
				<input type="hidden" id="paymentId" name="paymentId" value="${payment.paymentId}">
				<input type="text" id="refundInfo" class="refundInfo" name="refundInfo" placeholder="상세 사유를 입력해주세요.">
				<button type="button" id="refundBtn" class="refundBtn">취소 요청</button>
			</form>
		</div>
	</div>
</main>

<script>
let cancelRequest = {
		version: 1,
		init: function() {
			$(document).on("click", "#refundBtn", () => {
				this.submit();
			});
		},
		
		submit:function() {
			let refundInfo = $("#refundInfo").val();
			
			if(!refundInfo) {
				alert("취소 사유를 입력해주세요.");
				return false;
			}
			
			$("#cancelForm").submit();
		}
}
cancelRequest.init();

</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>