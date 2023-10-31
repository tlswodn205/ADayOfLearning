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
						<img alt="" src="https://picsum.photos/120/120">
						<div class="classItem">
							<h4>${reserve.title}</h4>
							<p>${reserve.sessionDate}</p>
						</div>
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
								<p>${reserve.createdAt}</p>
							</div>
							<div class="row">
								<p>예약처리상태</p>
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
						<p>40,000원</p>
					</div>
					<div class="payItem sum">
						<p>최종 결제 금액</p>
						<p>40,000원</p>
					</div>
					<div class="btn">
						<a href="#" class="btn_blue requestBtn" onClick="nicepayStart();">취소</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>