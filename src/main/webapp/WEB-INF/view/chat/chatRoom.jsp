<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="/js/chatRoom.js"></script>
<link rel="stylesheet" href="/css/chatRoom.css">
<div id="chat">
	<div class="chatRoomRoot">
		<div style="font-weight: bold; padding: 5px; border: 1px solid;">
			${principal.username}
		</div>
		<input type="hidden" id="username" value="${principal.username}">
		<input type="hidden" id="userId" value="${principal.userId}">
		<div class="chatRoomList" id="chatRoomList">
		<c:forEach var="chatRoom" items="${chatRoomList}">
			<div class="chatRoom">
				<input type="hidden" id="chatRoomId" value="${chatRoom.chatRoomId}">
				<input type="hidden" id="chatUserId" value="${chatRoom.userId}">
				<input type="hidden" id="chatUsername" value="${chatRoom.username}">
				<a class="chatUsername" onclick="chatRoom(this)">${chatRoom.username}</a>
				<c:choose>
					<c:when test="${chatRoom.viewCount > 0}">
						<span class="newMessage" id="newMessage">new</span>
					</c:when>
					<c:otherwise>
						<span class="newMessage" id="newMessage" style="display : none">new</span>
					</c:otherwise>
				</c:choose>
				<a class="chatDelete" id="chatLeave" onclick="chatLeave(this)">나가기</a>
			</div>
		</c:forEach>
		</div>
	</div>
	<div class="chatView">
		<input type="hidden" id="nowChatRoomId" value="${newChat.chatRoomId}">
		<input type="hidden" id="nowUserId" value="${newChat.userId}">
		<input type="hidden" id="nowUsername" value="${newChat.username}">
		
		<div id="chatPerson">${newChat.username}</div>
		<hr>
		<div class="chatContent" id="chatContent"></div>
		<input type="text" id="chatMessage">
		<button type="button" id="chatInput">전송</button>
	</div>
</div>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>