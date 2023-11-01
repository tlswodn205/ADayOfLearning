<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<link rel="stylesheet" href="/css/payRequest.css">

<!-- 아래 js는 PC 결제창 전용 js입니다.(모바일 결제창 사용시 필요 없음) -->
<script src="https://web.nicepay.co.kr/v3/webstd/js/nicepay-3.0.js"
	type="text/javascript"></script>
<script type="text/javascript">
	//결제창 최초 요청시 실행됩니다.
	function nicepayStart() {
		if (checkPlatform(window.navigator.userAgent) == "mobile") {//모바일 결제창 진입
			document.payForm.action = "https://web.nicepay.co.kr/v3/v3Payment.jsp";
			document.payForm.acceptCharset = "euc-kr";
			document.payForm.submit();
		} else {//PC 결제창 진입
			goPay(document.payForm);
		}
	}

	//[PC 결제창 전용]결제 최종 요청시 실행됩니다. <<'nicepaySubmit()' 이름 수정 불가능>>
	function nicepaySubmit() {
		document.payForm.submit();
	}

	//[PC 결제창 전용]결제창 종료 함수 <<'nicepayClose()' 이름 수정 불가능>>
	function nicepayClose() {
		alert("결제가 취소 되었습니다");
	}

	//pc, mobile 구분(가이드를 위한 샘플 함수입니다.)
	function checkPlatform(ua) {
		if (ua === undefined) {
			ua = window.navigator.userAgent;
		}

		ua = ua.toLowerCase();
		var platform = {};
		var matched = {};
		var userPlatform = "pc";
		var platform_match = /(ipad)/.exec(ua) || /(ipod)/.exec(ua)
				|| /(windows phone)/.exec(ua) || /(iphone)/.exec(ua)
				|| /(kindle)/.exec(ua) || /(silk)/.exec(ua)
				|| /(android)/.exec(ua) || /(win)/.exec(ua) || /(mac)/.exec(ua)
				|| /(linux)/.exec(ua) || /(cros)/.exec(ua)
				|| /(playbook)/.exec(ua) || /(bb)/.exec(ua)
				|| /(blackberry)/.exec(ua) || [];

		matched.platform = platform_match[0] || "";

		if (matched.platform) {
			platform[matched.platform] = true;
		}

		if (platform.android || platform.bb || platform.blackberry
				|| platform.ipad || platform.iphone || platform.ipod
				|| platform.kindle || platform.playbook || platform.silk
				|| platform["windows phone"]) {
			userPlatform = "mobile";
		}

		if (platform.cros || platform.mac || platform.linux || platform.win) {
			userPlatform = "pc";
		}

		return userPlatform;
	}
</script>
<main>
	<div id="payRequest">
		<div class="title">
			<h1>예약 요청 및 결제</h1>
		</div>
		<div class="content">
			<form name="payForm" method="post" action="payResult">
				<div class="formContent">
					<div class="contentLeft">
						<div class="sectionBox">
							<h2>예약 정보</h2>
							<div class="sectionBox-area">
								<img alt="" src="https://picsum.photos/120/120">
								<div class="reserveItem">
									<!-- 상점 아이디 -->
									<input type="hidden" name="MID" value="${payRequest.merchantID}">
									<!-- 결제 상품명 -->
									<h4>클래스명 <input type="text" name="GoodsName"	value="${payRequest.goodsName}"></h4>
									<p>11월 7일 (화요일) 11시 00분</p>
								</div>
							</div>
						</div>

						<div class="sectionBox">
							<h2>신청자 정보</h2>
							<div class="sectionBox-area">
								<div>
									<div class="row">
										<!-- 구매자명 -->
										<p>예약자</p>
										<p>김그린</p>
										<input type="text" name="BuyerName"
											value="${payRequest.buyerName}">
									</div>
									<div class="row">
										<!-- 구매자 연락처 -->
										<p>연락처</p>
										<p>010-1234-1234</p>
										<input type="text" name="BuyerTel" value="${payRequest.buyerTel}">
									</div>
									<div class="row">
										<!-- 구매자 이메일 -->
										<p>이메일</p>
										<p>aaa@naver.com</p>
										<input type="text" name="BuyerEmail" value="${payRequest.buyerEmail}">
									</div>
								</div>
							</div>
						</div>

						<!-- <div class="sectionBox">
							<h2>결제 수단</h2>
							<div class="sectionBox-area">
								<div>
									<div class="payType">
										<div class="payTypeBtn">일반결제</div>
										<div class="payTypeBtn">카카오페이</div>
									</div>
									<div class="payNotice">
										<p>클래스 환불 정책</p>
										<p>개인정보 제 3자 제공</p>
									</div>
								</div>
							</div>
						</div> -->

					</div>
					<div class="contentRight">
						<div class="payInfo">
							<h2>결제 정보</h2>
							<div class="amount classAmount">
								<p>클래스 금액</p>
								<p>40,000원</p>
								<!-- 결제금액 -->
								<input type="text" name="Amt" value="${payRequest.price}">
							</div>
							<div class="amount sum">
								<p>최종 결제 금액</p>
								<p>40,000원</p>
							</div>
							<div class="submitBtn">
								<!-- 결제 수단 -->
								<input type="hidden" name="PayMethod" value="">
								<!-- 상품 주문번호 -->
								<input type="hidden" name="Moid" value="${payRequest.moid}">
								<!-- 인증완료 결과처리 URL(모바일 결제창 전용)PC 결제창 사용시 필요 없음 -->
								<input type="hidden" name="ReturnURL" value="">
								<!-- 가상계좌입금만료일(YYYYMMDD) -->
								<input type="hidden" name="VbankExpDate" value="">
								<!-- 옵션 -->
								<input type="hidden" name="GoodsCl" value="1" />
								<!-- 상품구분(실물(1),컨텐츠(0)) -->
								<input type="hidden" name="TransType" value="0" />
								<!-- 일반(0)/에스크로(1) -->
								<input type="hidden" name="CharSet" value="utf-8" />
								<!-- 응답 파라미터 인코딩 방식 -->
								<input type="hidden" name="ReqReserved" value="" />
								<!-- 상점 예약필드 -->

								<!-- 변경 불가능 -->
								<input type="hidden" name="EdiDate"
									value="${payRequest.ediDate}" />
								<!-- 전문 생성일시 -->
								<input type="hidden" name="SignData"
									value="${payRequest.hashString}" />
								<!-- 해쉬값 -->

								<a href="#" class="btn_blue requestBtn" onClick="nicepayStart();">요청</a>
							</div>
						</div>
					</div>

				</div>
			</form>
		</div>
	</div>
</main>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
