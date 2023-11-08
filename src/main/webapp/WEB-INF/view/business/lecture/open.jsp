<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/business/layout/header.jsp"%>
<main>
	<div class="mainTop">
		<div class="title">강의 예약 생성</div>
	</div>
	<div id="businessOpenSession" class="mainColumn">
		<form action="/business/open" id="businessOpenSessionForm" method="post">
			<div class="DetailColumn">
				<input type="hidden" name="lectureId" id="sessionLecId" value="${lectureId}">
				<table>
					<tr>
						<td>아이디</td>
						<td><span>${businessUserData.username}</span></td>
					</tr>
					<tr>
						<td>강의 제목</td>
						<td><input type="text" readonly="readonly" value="${lecture.title}"></td>
					</tr>
					<tr>
						<td>예약 시간*</td>
						<td><input type="datetime-local" name="sessionDate" id="sessionDate"  > ※ 시, 분은 마우스 휠로 조정 가능</td>
					</tr>

				</table>
				<div class="detailColumnBottom">
					<input type="button" class="deleteBtn" id="openSessionGoBack" value="뒤로 가기"> <input type="button" class="inputBtn" id="openSessionSubmit" value="예약 등록">
				</div>
			</div>
		</form>
	</div>
</main>

<script>

let businessOpenSession = {
    version: 1,
    init: function() {
    	$(document).on("click", "#openSessionGoBack", ()=>{
    		this.goBack();
    	});
    	$(document).on("click", "#openSessionSubmit", ()=>{
    		this.openSession();
    	});
    },
    goBack:function(){
    	window.history.back();
    	
    },
    openSession:function(){
    	
    	let dateString = $("#sessionDate");
    	
    	if(!dateString) {
    		alert('날짜와 시간을 입력해주세요.');
    	} else {
    		let isConfirm = confirm("예약을 등록 하시겠습니까?");

    		if(!isConfirm){
    			return false;
    		} else {
    			$('#businessOpenSessionForm').submit();
    		}
    	}
    },
};

businessOpenSession.init();

</script>

<%@ include file="/WEB-INF/view/business/layout/footer.jsp"%>