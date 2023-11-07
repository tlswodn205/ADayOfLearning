<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>

<link rel="stylesheet" href="/css/userPage.css">

<main>
	<div id="userPage" class="mainColumn">
		<form action="/user/updateUserData" method="post" id="updateUserData" >
			<c:choose>
				<c:when test="${!myPageRequestDto.kakao}">
					<div class="wideDiv">
					<i class="fa-solid fa-user"></i>
					<input type="text" id="username" name="username" placeholder="아이디" readonly value="${myPageRequestDto.username}">
					</div>
					<br>
					
					<div class="wideDiv">
					<input type="password" id="password" name="password" placeholder="비밀번호">
					</div>
					<br>
					
					<div class="wideDiv">
					<input type="password" id="passwordCheck" name="passwordCheck" placeholder="비밀번호 확인">
					</div>
					<br>
				</c:when>
				
				<c:otherwise>
					<input type="hidden" id="username" name="username" placeholder="아이디" value="${myPageRequestDto.username}" readonly>
					<input type="hidden" id="password" name="password" placeholder="비밀번호" value="">
					<input type="hidden" id="passwordCheck" name="passwordCheck" placeholder="비밀번호 확인" value="">
				</c:otherwise>
			</c:choose>
			
			<div class="wideDiv">
				<i class="fa-solid fa-newspaper"></i>
				<input type="text" id="name" name="name" placeholder="이름" value="${myPageRequestDto.name}">
			</div>
			<br>
			
			<div class="wideDiv">
					<i class="fa-solid fa-envelope"></i>
			<input type="text" id="email" name="email" placeholder="이메일" readonly value="${myPageRequestDto.email}">
			</div>

			<br>
			<div class="flexDiv">
				<div class="narrowDiv">
					<i class="fa-solid fa-address-book"></i>
					<input type="text" id="address" name="address" placeholder="주소" readonly value="${myPageRequestDto.address}">
				</div>
				<input type="button" id="openZipSearch" value="주소 확인">
			</div>
			<br>
			
			<div class="wideDiv">
				<i class="fa-solid fa-address-book"></i>
				<input type="text" id="addressDetail" name="addressDetail" placeholder="상세주소" value="${myPageRequestDto.addressDetail}">
			</div>
			<br>
			
			<div class="wideDiv">
				<i class="fa-solid fa-phone"></i>
				<input type="number" id="phoneNumber" name="phoneNumber" placeholder="전화번호" value="${myPageRequestDto.phoneNumber}">
			</div>
			<br>		
			
			<div class="wideDiv">	
				<i class="fa-solid fa-cake-candles"></i>
			<input type="date" id="birthday" name="birthday"  value="${myPageRequestDto.birthday}">
			</div>
			<br>
			<input type="button" class="submitBtn" id="updateUserDataBtn" value="개인정보저장">
		</form>
	</div>
</main>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>


let updateUserData = {
	version:1,
	init : function(){
		$(document).ready( ()=>{
			this.ready();
		});
		
		$(document).on("click", "#updateUserDataBtn", ()=>{
			this.submit();
		});
		$(document).on("click", "#openZipSearch", ()=>{
			this.findAddress();
		});
	},
	ready:function(){
		let now_utc = Date.now();
		
		let timeOff = new Date().getTimezoneOffset()*60000;
		
		let today = new Date(now_utc-timeOff).toISOString().split("T")[0];
		
		$('#birthday').attr("max", today);
	},
	submit : function(){
		let password = $("#password").val();
		let passwordCheck = $("#passwordCheck").val();
		let name = $("#name").val();
		let email = $("#email").val();
		let address = $("#address").val();
		let phoneNumber = $("#phoneNumber").val();
		let birthday = $("#birthday").val();
		
		
		if(password != passwordCheck){
			alert("비밀번호와 비밀번호 확인이 다릅니다.");
			return false;
		}
		
		if(!name){
			alert("이름을 입력해주세요.");
			return false;
		}	
		
		if(!email){
			alert("이메일을 입력해주세요.");
			return false;
		}
		
		if(!address){
			alert("주소를 입력해주세요.");
			return false;
		}

		if(!phoneNumber){
			alert("전화번호를 입력해주세요.");
			return false;
		}
		
		if(!birthday){
			alert("생일을 입력해주세요.");
			return false;
		}
		
		$("#updateUserData").submit();
	},
	findAddress : function(){
		new daum.Postcode({
			oncomplete : function(data) {
				var addr = '';
				if (data.userSelectedType === 'R') {
					addr = data.roadAddress;
				} else {
					addr = data.jibunAddress;
				}

				$("#address").val(addr);
				$("#addressDetail").focus();
			}
		}).open();
	}
}
updateUserData.init();
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>