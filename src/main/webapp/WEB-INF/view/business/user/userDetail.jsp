<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/business/layout/header.jsp" %>
<main>
	<div class="mainTop">
		<div class="title">내 정보보기</div>
	</div>
	<div id="userDetail" class="mainColumn">
		<form action="/business/businessUpdate" id="businessUpdate" method="post">
			<div class="DetailColumn">
				<input type="hidden" name="businessNumber" id="businessNumber" value="${businessUserData.businessNumber}">
				<table>	
					<tr>
						<td>아이디</td>
						<td><span>${businessUserData.username}</span></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="text" name="password"></td>
					</tr>
					<tr>
						<td>상호명</td>
						<td><input type="text" name="businessName" id="businessName" value="${businessUserData.businessName}"></td>
					</tr>
					<tr>
						<td>대표자명</td>
						<td><input type="text" name="CEOname" id="CEOname" value="${businessUserData.CEOname}"></td>
					</tr>
					<tr>
						<td>사업장 주소</td>
						<td><input type="text" name="businessAddress" id="businessAddress" value="${businessUserData.businessAddress}"></td>
					</tr>
					<tr>
						<td>사업장 상세주소</td>
						<td><input type="text" name="businessAddressDetail" id="businessAddressDetail" value="${businessUserData.businessAddressDetail}"></td>
					</tr>
					<tr>
						<td>사업장 전화번호</td>
						<td>
							<input type="number" class="tel" id="tel1"> - 
							<input type="number" class="tel" id="tel2"> - 
							<input type="number" class="tel" id="tel3"> 
						</td>
					</tr>
					<tr>
						<td>사업자 등록번호</td>
						<td><span>${businessUserData.businessRegistrationNumber}</span></td>
					</tr>
					<tr>
						<td>사업자 등록증</td>
						<td><img src="${businessUserData.businessRegistrationImg}"></td>
					</tr>
				</table>
				
				<input type="button" class="inputBtn" id="businessUpdateBtn" value="정보변경">
			</div>
		</form>
	</div>
</main>

<script>

let BusinessUserDetail = {
    version: 1,
    init: function() {
    	$(document).on("click", "#businessUpdateBtn", ()=>{
    		this.update();
    	});

		$(document).ready( ()=>{
			this.ready();
		});
    },
	ready:function(){
		let phoneNumber = $('#businessNumber').val();
		let phoneNumbers = phoneNumber.split('-');
		
		
		$('#tel1').val(phoneNumbers[0]);
		$('#tel2').val(phoneNumbers[1]);
		$('#tel3').val(phoneNumbers[2]);
	},
    update:function(){
    	let isChanged = confirm("정보를 변경하시겠습니까?");

    	if(!isChanged){
    		return false;
    	}
    	
		let businessName = $("#businessName").val();
		let CEOname = $("#CEOname").val();
		let businessAddress = $("#businessAddress").val();
		
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
		

		$("#businessUpdate").submit();
    },
    
    
};

BusinessUserDetail.init();

</script>

<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>