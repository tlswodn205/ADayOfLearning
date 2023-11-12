<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>

<link rel="stylesheet" href="/css/userPage.css">

<main>
	<div id="userPage" class="mainColumn">
		<form action="/user/businessRequest" method="post" id="businessRequest"  enctype="multipart/form-data">
			
			<div class="wideDiv">
				<i class="fa-solid fa-briefcase"></i>
				<input type="text" id="businessName" name="businessName" placeholder="상호명" value="">
			</div>
			<br>
			
			<div class="wideDiv">
				<i class="fa-solid fa-newspaper"></i>
				<input type="text" id="CEOname" name="CEOname" placeholder="대표자명" value="${person.name}">
			</div>
			<br>
			
			
			<div class="flexDiv">
				<div class="narrowDiv">
				<i class="fa-solid fa-address-book"></i>
				<input type="text" id="businessAddress" name="businessAddress" placeholder="사업장 주소" readonly value="${person.address}">
				</div>
				<input type="button" id="openZipSearch" value="주소 확인">
			</div>
			<br>
			
			<div class="wideDiv">
				<i class="fa-solid fa-address-book"></i>
				<input type="text" id="businessAddressDetail" name="businessAddressDetail" placeholder="상세주소" value="${person.addressDetail}">
			</div>
			<br>
			
			<div class="wideDiv">
				<i class="fa-solid fa-phone"></i>
				<div class="telbox">
					<input type="hidden" id="businessNumber" name="businessNumber" placeholder="사업장번호" value=""> 
					<input type="number" class="tel" id="tel1" maxlength="3"> - 
					<input type="number" class="tel" id="tel2" maxlength="4"> - 
					<input type="number" class="tel" id="tel3" maxlength="4"> 
				</div>
			</div>
			<br>
			
			<div class="wideDiv">
				<input type="text" id="businessRegistrationNumber" name="businessRegistrationNumber" placeholder="사업자 등록번호" value="">
			</div>
			<br>
			
			<div class="wideDiv">
				<i class="fa-regular fa-file"></i>
				<input type="file" id="businessRegistration" name="businessRegistration" placeholder="사업자 등록증" value="" accept="image/*">
			</div>
			<br>
			
			<input type="button" class="submitBtn" id="businessRequestButton" value="판매자 신청">
		</form>
	</div>
</main>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>

let BusinessRequest = {
    version: 1,
    init: function() {
    	$(document).on("click", "#businessRequestButton", ()=>{
    		this.businessRequest();
    	});
    	
    	$(document).on("click", "#openZipSearch", ()=>{
    		this.findAddress();
    	});

		$(document).on("input", "#tel1", ()=>{
			this.telChange(1,3);
		});
		$(document).on("input", "#tel2", ()=>{
			this.telChange(2,4);
		});
		$(document).on("input", "#tel3", ()=>{
			this.telChange(3,4);
		});
    },
    findAddress: function() {
   		new daum.Postcode({
   			oncomplete : function(data) {
   				var addr = '';
   				if (data.userSelectedType === 'R') {
   					addr = data.roadAddress;
   				} else {
   					addr = data.jibunAddress;
   				}

   				$("#businessAddress").val(addr);
   				$("#businessAddressDetail").focus();
   			}
   		}).open();
    },
    
    businessRequest : function(){
		let businessName = $("#businessName").val();
		let CEOname = $("#CEOname").val();
		let businessAddress = $("#businessAddress").val();
		let businessRegistrationNumber = $("#businessRegistrationNumber").val();
		let businessRegistration = $("#businessRegistration").val();

    	let tel1 = $('#tel1').val();
    	let tel2 = $('#tel2').val();
    	let tel3 = $('#tel3').val();

    	let businessNumber = tel1+ "-"+tel2+'-'+tel3;
    	$("#businessNumber").val(businessNumber);
    	
		if(!businessName){
			alert("상호명을 입력해주세요.");
			return false;
		}
		
		if(!CEOname){
			alert("대표자명을 입력해주세요.");
			return false;
		}
		
		if(!businessAddress){
			alert("주소를 입력해주세요.");
			return false;
		}

		if(tel1.toString().length==0 || tel2.toString().length==0 || tel3.toString().length==0){
    		alert("사업장 전화번호를 입력해주세요.");
    		return false;
		}
    	
    	if(!(tel1.toString().length==3)){
    		alert("사업장 전화번호를 다시 입력해주세요.");
    		return false;
    	}

    	if(!(tel2.toString().length==3 || tel2.toString().length==4)){
    		alert("사업장 전화번호를 다시 입력해주세요.");
    		return false;
    	}

    	if(!(tel3.toString().length==4)){
    		alert("사업장 전화번호를 다시 입력해주세요.");
    		return false;
    	}
		
		if(!businessRegistrationNumber){
			alert("사업자 등록 번호를 입력해주세요.");
			return false;
		}
		
		if(!businessRegistration){
			alert("사업자 등록증을 첨부 해주세요.");
			return false;
		}
		
		
		$("#businessRequest").submit();
    },	
    telChange: function(i, mLength) {
		let str = $('#tel' + i).val();
 		if($('#tel' + i).val().length > mLength) {
 			let number = str.substring(0,mLength);
 			$('#tel' + i).val(number);
		}
		if(str.length >= mLength && i < 3) {
			$('#tel'+ (i + 1)).focus();
		} else if (i === 3) {
			$('#businessRegistrationNumber').focus();
		}
	}
	 
};
	
BusinessRequest.init();

</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>