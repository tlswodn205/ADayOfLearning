<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="/js/chatRoom.js"></script>
<link rel="stylesheet" href="/css/chatRoom.css">
<div id="chat">
	<div class="chatRoomList">
		<div style="font-weight: bold; padding: 5px; border: 1px solid;">
			${principal.username}
		</div>
		<input type="hidden" id="username" value="${principal.username}">
		<input type="hidden" id="userId" value="${principal.userId}">
		<c:forEach var="chatRoom" items="${chatRoomList}">
			<div class="chatRoom" onclick="chatRoom(this)">
				<input type="hidden" id="chatRoomId" value="${chatRoom.chatRoomId}">
				<input type="hidden" id="chatUserId" value="${chatRoom.userId}">
				<span id="chatUsername">${chatRoom.username}</span>
			</div>
		</c:forEach>
	</div>
	<div class="chatView">
		
		<input type="hidden" id="nowChatRoomId" value="${chatEnter.chatRoomId}">
		<input type="hidden" id="nowUserId" value="${chatEnter.userId}">
		<input type="hidden" id="nowUsername" value="${chatEnter.username}">
		
		<div id="chatPerson">${chatEnter.username}</div>
		<hr>
		<div class="chatContent" id="chatContent"></div>
		<input type="text" id="chatMessage">
		<button type="button" id="chatInput">전송</button>
	</div>
</div>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>