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
						<div class="sectionBox note">
							<p><strong>예약하려는 클래스의 정보를 확인해주세요.</strong></p>
							<p>클래스 제목, 위치, 시간이 올바른지 확인하십시오.</p>
						</div>
						<div class="sectionBox">
							<h2>예약 정보</h2>
							<div class="sectionBox-area">
								<img src="${thumbnail}" width="120px" height="120px">
								<div class="reserveItem">
									<!-- 상점 아이디 -->
									<input type="hidden" name="MID" value="${payRequest.merchantID}">
									<!-- 클래스명(결제 상품명) -->
									<input type="hidden" name="lectureSessionId" value="${session.lectureSessionId}">
									<input type="text" readonly id="payRequsetTitle" class="payRequsetTitle" name="GoodsName" value="${lecture.title}" width="100px;">
									<p>${lecture.address} ${lecture.addressDetail}</p>
									<p><fmt:formatDate pattern="yyyy년 MM월 dd일 (E요일), HH시 mm분" value="${session.sessionDate}"/></p>
									<%-- <input type="text" readonly id="payRequsetTitle" class="payRequsetTitle" name="GoodsName" value="${lectureData.title}"> --%>
									<!-- <p id="sessionDate"></p> -->
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
										<input type="text" readonly name="BuyerName" value="${buyer.name}">
									</div>
									<div class="row">
										<!-- 구매자 연락처 -->
										<p>연락처</p>
										<input type="text" readonly name="BuyerTel" value="${buyer.phoneNumber}">
									</div>
									<div class="row">
										<!-- 구매자 이메일 -->
										<p>이메일</p>
										<input type="text" readonly name="BuyerEmail" value="${buyer.email}">
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
								<!-- 결제금액 -->
								<p>클래스 금액</p>
								
								<p><fmt:formatNumber value="${lecture.price}" groupingUsed="true"/>원</p>
								<%-- <input type="text" readonly name="Amt" value="${lecture.price}"> --%>
								<!-- <p id="payRequsetPrice"></p> -->
							</div>
							<div class="amount sum">
								<p>최종 결제 금액</p>
								<input type="hidden" readonly name="Amt" value="${lecture.price}">
								<p><fmt:formatNumber value="${lecture.price}" groupingUsed="true"/>원</p>
								<%-- <input type="text" readonly name="Amt" value="${lectureData.price}"> --%>
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
								<input type="hidden" name="EdiDate" value="${payRequest.ediDate}" />
								<!-- 전문 생성일시 -->
								<input type="hidden" name="SignData" value="${payRequest.hashString}" />
								<!-- 해쉬값 -->

								<a href="#" class="btn_blue requestBtn" onClick="nicepayStart();">결제하기</a>
							</div>
						</div>
					</div>

				</div>
			</form>
		</div>
	</div>
</main>

<script src="/js/payRequest.js"></script>
<script type="text/javascript">
/* 
let lectureData = ${lecture};
let sessionData = ${session};
 */
	
</script>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
