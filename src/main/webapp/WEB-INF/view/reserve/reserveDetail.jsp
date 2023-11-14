<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>

<link rel="stylesheet" href="/css/reserveDetail.css">

<main>
	<div id="reserveDetail">
		<div class="title">
			<h1>예약 상세</h1>
		</div>
		<div class="content">
			<div class="contentLeft">

				<div class="sectionBox">
					<h2>판매자 정보</h2>
					<div class="sectionBox-area">
						<div>
							<div class="row">
								<p>판매자명</p>
								<p>${reserve.name}</p>
							</div>
							<div class="row">
								<p>연락처</p>
								<p>${reserve.phoneNumber}</p>
							</div>
							<div class="row">
								<p>주소</p>
								<p>${reserve.address} ${reserve.addressDetail}</p>
							</div>
						</div>
					</div>
				</div>

				<div class="sectionBox">
					<h2>예약 클래스</h2>
					<div class="sectionBox-area">
						<div class="imgContainer">
							<img alt="썸네일" src="${reserve.img}">
						</div>
						<div class="classItem">
							<h4>${reserve.title}</h4>
							<p><fmt:formatDate pattern="yyyy년 MM월 dd일 (E요일), HH시 mm분" value="${reserve.sessionDate}"/></p>
						</div>
					</div>
					<div class="listBtnBox">
						<input type="hidden" id="lectureId" value="${reserve.lectureId}">
						<input type="hidden" id="userId" value="${reserve.userId}">
						<button type="button" class="listBtn" id="reviewBtn">후기 작성하기</button>
						<button type="button" class="listBtn" id="inquiryBtn">문의하기</button>
					</div>
				</div>

				<div class="sectionBox">
					<h2>예약 정보</h2>
					<div class="sectionBox-area">
						<div>
							<div class="row">
								<p>예약번호</p>
								<p>${reserve.reserveId}</p>
							</div>
							<div class="row">
								<p>예약 신청일</p>
								<p><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${reserve.createdAt}" /></p>
							</div>
							<div class="row">
								<p>처리상태</p>
								<p>${reserve.state}</p>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="contentRight">
				<div class="payInfo">
					<h2>결제 정보</h2>
					<div class="payItem">
						<p>결제 수단</p>
						<p>${reserve.paymentKind}</p>
					</div>
					<div class="payItem">
						<p>클래스 금액</p>
						<p><fmt:formatNumber value="${reserve.amount}" groupingUsed="true"/>원</p>
					</div>
					<div class="payItem sum">
						<p>최종 결제 금액</p>
						<p><fmt:formatNumber value="${reserve.amount}" groupingUsed="true"/>원</p>
					</div>
					<c:if test="${reserve.state == '결제 완료'}">
						<a href="/payment/cancelRequest/${reserve.paymentId}" class="btn_blue state requestBtn">결제 취소</a>
					</c:if>
					<c:if test="${reserve.state == '취소 요청'}">
						<p class="btn_blue state">취소 대기</p>
					</c:if>
					<c:if test="${reserve.state == '취소 완료'}">
						<p class="btn_blue state">취소 완료</p>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</main>

<script>
let detailInit = {
		version: 1,
		init: function() {
			$(document).ready(() => {
				$('#reviewBtn').on('click', () => this.review());
				$('#inquiryBtn').on('click', () => this.inquiry());
			});
		},
		review: function() {
			let lectureId = $('#lectureId').val();
			location.href = '/lecture/detail?id=' + lectureId + '#detailReview';
		},
		inquiry: function() {
			let userId = $('#userId').val();
			location.href = '/chat/room?userId=' + userId;
		}
}
detailInit.init();
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>