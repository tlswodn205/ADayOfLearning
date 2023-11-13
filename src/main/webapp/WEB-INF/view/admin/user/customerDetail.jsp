<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/admin/layout/header.jsp" %>
<main>
	<div class="mainTop">
		<div class="title">구매자 상세보기</div>
	</div>
	<div id="userDetail" class="mainColumn">
		<form action="/admin/updateUser/${customerUserData.userId}" id="updateUser" method="post">
			<div class="DetailColumn">
				<input type="hidden" id="phoneNumber" name="phoneNumber" value="${customerUserData.phoneNumber}">
				<input type="hidden" id="userId" value="${customerUserData.userId}">
				<table>	
					<tr>
						<td>아이디</td>
						<td><span>${customerUserData.username}</span></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="text" name="password"></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" id="name" name="name" value="${customerUserData.name}"></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><input type="text" id="address"  name="address" value="${customerUserData.address}">
						<input type="button" id="openZipSearch" value="주소 확인">
						</td>
					</tr>
					<tr>
						<td>상세주소</td>
						<td><input type="text" name="addressDetail" value="${customerUserData.addressDetail}"></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" name="email" value="${customerUserData.email}"></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td>
							<input type="number" class="tel" id="tel1" min="0" max="999"> - 
							<input type="number" class="tel" id="tel2" min="0" max="9999"> - 
							<input type="number" class="tel" id="tel3" min="0" max="9999"> 
						</td>
					</tr>
					<tr>
						<td>생년월일</td>
						<td><input type="date" id="birthday" name="birthday" value="${customerUserData.birthday}"></td>
					</tr>
				</table>
				<div class="detailColumnBottom">
					<input type="button" class="updateBtn" id="userUpdateBtn" value="유저 데이터 수정">
					<input type="button" class="deleteBtn" id="userDeleteBtn" value="유저 삭제">
				</div>
			</div>
		</form>
	</div>
</main>

<script>

let adminCustomerDetail = {
    version: 1,
    init: function() {
    	$(document).on("click", "#userUpdateBtn", ()=>{
    		this.update();
    	});
    	$(document).on("click", "#userDeleteBtn", ()=>{
    		this.delete();
    	});
    	$(document).on("click", "#openZipSearch", ()=>{
    		this.findAddress();
    	});
		$(document).ready( ()=>{
			this.ready();
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
	ready:function(){
		let phoneNumber = $('#phoneNumber').val();
		let phoneNumbers = phoneNumber.split('-');
		
		$('#tel1').val(phoneNumbers[0]);
		$('#tel2').val(phoneNumbers[1]);
		$('#tel3').val(phoneNumbers[2]);
	},
    update:function(){
    	let isChanged = confirm("유저 정보를 수정하시겠습니까?");

		let name = $("#name").val();
		let address = $("#address").val();
		let birthday = $("#birthday").val();

    	let tel1 = $('#tel1').val();
    	let tel2 = $('#tel2').val();
    	let tel3 = $('#tel3').val();

    	let phoneNumber = tel1+ "-"+tel2+'-'+tel3;
    	$("#phoneNumber").val(phoneNumber);
    	
    	if(!isChanged){
    		return null;
    	}
		
		if(!name){
			alert("이름을 입력해주세요.");
			return false;
		}	
		
		if(!address){
			alert("주소를 입력해주세요.");
			return false;
		}

		if(tel1.toString().length==0 || tel2.toString().length==0 || tel3.toString().length==0){
    		alert("전화번호를 입력해주세요.");
    		return false;
		}
    	
    	if(!(tel1.toString().length==3)){
    		alert("전화번호를 다시 입력해주세요.");
    		return false;
    	}

    	if(!(tel2.toString().length==3 || tel2.toString().length==4)){
    		alert("전화번호를 다시 입력해주세요.");
    		return false;
    	}

    	if(!(tel3.toString().length==4)){
    		alert("전화번호를 다시 입력해주세요.");
    		return false;
    	}
		
		if(!birthday){
			alert("생일을 입력해주세요.");
			return false;
		}
    	
		$("#updateUser").submit();
    },

    delete:function(){
    	let isDelete = confirm("유저 정보를 삭제하시겠습니까?");

    	if(!isDelete){
    		return false;
    	}
    	
    	let realDelete = confirm("정말 유저 정보를 삭제하시겠습니까?");

    	if(!realDelete){
    		return false;
    	}
    	
       	let userID = $("#userId").val();
       	let URL = "/admin/deleteUser/"+userID;
       	
   		fetch(URL, {
   		    method: "delete", 
   		}).then(response => response.json())
   		  .then(function(jsonData){
   	       	if(jsonData==1){
   			  	alert("삭제 완료했습니다.");
   			  	location.href="/admin/customerList";
   			}
   		}).catch(error => {
      		 alert("삭제 실패했습니다.");	
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
	telChange: function(i, mLength) {
		let str = $('#tel' + i).val();
 		if($('#tel' + i).val().length > mLength) {
 			let number = str.substring(0,mLength);
 			$('#tel' + i).val(number);
		}
		if(str.length >= mLength && i < 3) {
			$('#tel'+ (i + 1)).focus();
		} else if (str.length >= mLength && i === 3) {
			$('#birthday').focus();
		}
	}
};

adminCustomerDetail.init();

</script>

<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>