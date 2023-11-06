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
				<input type="number" id="businessNumber" name="businessNumber" placeholder="사업장번호" value="">
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

   				$("#address").val(addr);
   				$("#addressDetail").focus();
   			}
   		}).open();
    },
    
    businessRequest : function(){
		let businessName = $("#businessName").val();
		let CEOname = $("#CEOname").val();
		let businessAddress = $("#businessAddress").val();
		let businessNumber = $("#businessNumber").val();
		let businessRegistrationNumber = $("#businessRegistrationNumber").val();
		let businessRegistration = $("#businessRegistration").val();
	
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
	
		if(!businessNumber){
			alert("사업장 번호를 입력해주세요.");
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
	 
};
	
BusinessRequest.init();

</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>