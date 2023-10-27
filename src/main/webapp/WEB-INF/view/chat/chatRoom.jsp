<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="/js/chat.js"></script>
<link rel="stylesheet" href="/css/chat.css">
<div id="chat">
	<div class="chatRoomList">
		<input type="text" id="userId" placeholder="userId">
		<c:forEach var="chatRoom" items="${chatRoomList}">
			<div class="chatRoom" onclick="chatRoom(this)">
				<input type="hidden" id="chatRoomId" value="${chatRoom.chatRoomId}">
				<span id="name">${chatRoom.name}</span>
			</div>
		</c:forEach>
	</div>
	<div class="chatView">
		<input type="hidden" id="nowChatRoomId" value="1">
		<input type="hidden" id="nowName" value="1">
		<div id="chatPerson"></div>
		<hr>
		<div id="chatContent"></div>
		<input type="text" id="chatMessage">
		<button type="button" id="chatInput">전송</button>
	</div>
</div>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>