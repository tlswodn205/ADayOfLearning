<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<main>
	<div id="signUp" class="mainColumn">
		<form action="/user/signUp" method="post" id="signUpForm">
			<input type="text" id="username" name="username" placeholder="아이디">
			<input type="button" id="usernameDuplicationCheck" value="회원 중복 확인">
			<br>
			<input type="password" id="password" name="password" placeholder="비밀번호">
			<br>
			<input type="password" id="passwordCheck" name="passwordCheck" placeholder="비밀번호 확인">
			<br>
			<input type="text" id="name" name="name" placeholder="이름">
			<br>
			<input type="text" id="email" name="email" placeholder="이메일">
			<input type="button" id="emailCheck" value="이메일 확인">
			<br>
			<input type="text" id="address" name="address" placeholder="주소" readonly>
			<input type="button" id="openZipSearch" value="주소 확인">
			<br>
			<input type="text" id="addressDetail" name="addressDetail" placeholder="상세주소">
			<br>
			<input type="number" id="phoneNumber" name="phoneNumber" placeholder="전화번호">
			<br>			
			<input type="date" id="birthday" name="birthday">
			<br>
			<div>
				<input type="checkbox" id="check1" name="check1" >
				<label for="check1">하루의배움이 본인의 개인 정보를 수집 및 이용하는 것에 동의합니다.*</label>
			</div>
			<br>
			<div>
				<input type="checkbox" id="check2" name="check2" >
				<label for="check2">하루의배움이 본인의 개인 정보를 제3자 제공 및 국외 이전 하는 것에 동의합니다.*</label>
			</div>
			<br>
			<div>
				<input type="checkbox" id="check3" name="check3" >
				<label for="check3">하루의배움의 새로운 활동, 전용 상품, 맞춤형 서비스에 대한 맞춤화된 소식과 최신 정보를 받는 것과, 본인의 관심사에 기반한 맞춤화된 서비스를 제공받는 것에 동의합니다.</label>
			</div>
			<br>
			<div>«프로필 만들기»를 선택함으로써, 귀하는 당사의 이용약관에 동의하고, 당사의 개인정보 보호정책을 확인했으며,
				자신의 하루의배움 프로필을 만들고자 함을 확인합니다. 상품과 서비스 제공에 필요한 개인 정보의 수집, 사용 및 제3자 제공에
				동의하지 않는 경우, 하루의배움의 상품과 서비스를 제공 받지 못할 수 있습니다.</div>
			<br>
			<details>
				<summary>자세히 보기</summary>
					<li>해당 문서는 본사의 개인 정보 보호 정책 전문입니다. 본사가 귀하에게 안내하고자 하는 요점은 다음과
						같습니다:</li>
					<li>귀하는 현재 하루의배움 프로필을 생성하고자 하고 있습니다. 이는 하루의배움으로 하여금 귀하에게 개인화되고
						맞춤화된 서비스를 제공하고, 귀하가 하루의배움에 요청하는 상품, 서비스 및 정보를 제공하고 귀하와 소통하는 것을 도울
						것입니다.</li>
					<li>귀하가 신청서에 기입한 모든 개인 정보와 귀하가 판매원 또는 다른 방식(예를 들어 소셜 미디어, 쿠키
						등)을 통해 자발적으로 공유한 다른 개인 정보(이하 “개인 정보”)는 보관 및 사용되고, 귀하의 하루의배움 프로필에
						연결될 것입니다. 귀하의 프로필은 귀하로부터 직접 받은 정보와 함께 본사의 소매점 및 타 상업적 파트너로부터 받은 정보를
						포함할 수 있습니다. 현지 법률에 따라, 본사는 귀하의 개인 정보를 귀하가 하루의배움을 이용하여 소통한 마지막 일시로부터
						5년간 보유할 것입니다. 마지막 소통은 고객에 의해 시작된 마지막 소통으로 정의되며 본사의 시스템이나 판매원에 의해
						추적할 수 있습니다. 그 예로 귀하에게 전화, 판매 이메일, 또는 대면이 마지막으로 제의되고, 그에 대해 귀하가 이메일에
						포함된 본사 웹사이트를 클릭하거나 전용 계정에 대한 제의에 따라 본사 매장에 가는 등 호의적으로 응답했을 때 등이
						있습니다. 하루의배움으로부터 온 이메일을 열어보는 것은 마지막 소통에 해당되지 않습니다. 이메일에 포함된 링크를 클릭하는
						것과 본사의 매장에 방문하는 것이 마지막 소통에 해당됩니다.</li>
					<li>본사가 국제적으로 운영되기 때문에, 본사는 귀하의 개인 정보를 하루의배움과 동일한 목적으로, 위에 명기된
						기간 동안 우리 그룹의 기업들과 세계 곳곳에 위치한 승인된 파트너들과 안전하게 공유할 수 있습니다(기업들과 승인된
						파트너들의 리스트는 다음 링크를 참고하시기 바랍니다:
						https://www.ADayOfLearning.com/kr/ko/st/privacy-landing). 본사는 귀하의 개인 정보를
						보호하기 위해 보안 장치를 시행할 것입니다.</li>
					<li>본사는 귀하가 본사와 공유한 연락처를 사용할 수 있습니다. 이는 귀하의 관심사와 쇼핑 습관에 대해 문자
						메시지, 멀티미디어 메시지, 이메일, 우편, 인터넷, 소셜 미디어 및 휴대폰 등의 다양한 경로를 통해 귀하와 소통하기
						위함입니다. 또, 이는 주로 자료 수집을 통해 본사의 서비스를 향상시키고, 위에 명기된 기간 동안 본사 웹사이트를 통해
						귀하에게 노출된 광고를 선별하여 그와 유사한 타겟 마케팅을 하기 위함입니다.</li>
					<li>개인 정보 보호법은 귀하에게 개인 정보 이동권과 더불어 귀하의 데이터에 대한 접근, 삭제, 수정 및 정정,
						처리에 대한 제한 및 반대의 권리와 같은 특정 권리를 부여할 수 있습니다. 귀하는 또한 유능한 규제 기관과 함께 항의를
						제기할 수 있습니다. 귀하는 언제든지 동의를 철회하거나 프로필을 삭제할 수 있습니다. 본사의 개인 정보 관리와 귀하의
						권리에 대한 더 많은 정보를 원하시면, privacy@ADayOfLearning.com으로 연락 주시기 바랍니다.</li>
			</details>
      <br>
	<div id="example3" class="g-recaptcha" data-sitekey="6LdlBDMoAAAAAJ5e259lVDfM02OcrCd5TftL9awD"data-callback="verifyCallback"></div>
      <input type="button" value="Submit" onclick="grecaptchaCheck()" >
			<input type="button" id="signUpBtn" value="회원가입">
		</form>
	</div>
</main>
             <script src="https://www.google.com/recaptcha/api.js" async defer></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
let usernameCheck = 0;
let emailCheck = 0;

$(document).ready(function(){
	$('#birthday').val();
});

$(document).on("change", "#username", function(){
	usernameCheck = 0;
});

$(document).on("click", "#usernameDuplicationCheck",async function(){
	let username = $('#username').val();
	let URL = "/user/usernameDuplicationCheck?username="+username;
	
	if(username.length == 0){
		alert("아이디를 입력해주세요");
		return false;
	}
	
	let response = await fetch(URL, {
	    method: "get", // *GET, POST, PUT, DELETE 등
	    mode: "cors", // no-cors, *cors, same-origin
	    cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
	    credentials: "same-origin", // include, *same-origin, omit
	    referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
	  });

   	if(response){
   		usernameCheck = 1;
   		alert("사용가능한 아이디 입니다");
   	}else{
   		alert("사용중인 아이디 입니다");
   	}
});


$(document).on("click", "#signUpBtn", function(){
	let username = $("#username").val();
	let password = $("#password").val();
	let passwordCheck = $("#passwordCheck").val();
	let name = $("#name").val();
	let email = $("#email").val();
	let address = $("#address").val();
	let phoneNumber = $("#phoneNumber").val();
	let birthday = $("#birthday").val();
	

	
	if(!username){
		alert("아이디를 입력해주세요.");
		return false;
	}
	
	if(!usernameCheck){
		alert("아이디 중복체크를 해주세요.");
		return false;
	}
	
	if(!password){
		alert("비밀번호를 입력해주세요.");
		return false;
	}
	
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
	
	if(!email){
		alert("이메일 인증을 해주세요.");
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
	
	if(!(check1&&check2)){
		alert("필수 동의 항목을 체크 해주세요.");
		return false;
	}
	
	$("#signUpForm").submit();
});

$(document).on("click", "#openZipSearch", function() {
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
})




      let widgetId1;
      

      
      
      async function grecaptchaCheck(){
    	let data = $("#ex3")[0];
    	let URL = "http://www.google.com/recaptcha/api/siteverify?secret=6LdlBDMoAAAAAP7BHcrmNBMclxHp2y-1_KBAczAB&response="+widgetId1;
        var form_data = new FormData();
    	let response = await fetch(URL, {
    	    method: "get", // *GET, POST, PUT, DELETE 등
    	    mode: "no-cors", // no-cors, *cors, same-origin
    	    cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
    	    credentials: "same-origin", // include, *same-origin, omit
    	    referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
    	  });
    	
    	console.log(response);
      };
      
      var verifyCallback = function(response) {
          alert(response);
          widgetId1= response;
        };
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>