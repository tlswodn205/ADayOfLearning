<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/business/layout/header.jsp" %>
<main>
	<div id="businessChatRoom">
		<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
		<script src="/js/chatRoom.js"></script>
		<link rel="stylesheet" href="/css/chatRoom.css">
		<div id="chat">
			<div class="chatRoomListContainer">
				<div class="myPerson">
					내정보 : ${principal.username}
				</div>
				<hr>
				<input type="hidden" id="username" value="${principal.username}">
				<input type="hidden" id="userId" value="${principal.userId}">
				<div class="chatRoomList" id="chatRoomList">
				<c:forEach var="chatRoom" items="${chatRoomList}">
					<div class="chatRoomContainer">
						<input type="hidden" id="chatRoomId" value="${chatRoom.chatRoomId}">
						<input type="hidden" id="chatUserId" value="${chatRoom.userId}">
						<input type="hidden" id="chatUsername" value="${chatRoom.username}">
						<div class="chatRoom" onclick="chatRoom(this)">
							<a class="chatTitle">${chatRoom.username}</a>
							<c:choose>
								<c:when test="${chatRoom.viewCount > 0}">
									<span class="newMessage" id="newMessage">${chatRoom.viewCount}</span>
								</c:when>
								<c:otherwise>
									<span class="newMessage" id="newMessage" style="display : none">${chatRoom.viewCount}</span>
								</c:otherwise>
							</c:choose>
						</div>
						<a class="chatLeave" id="chatLeave" onclick="chatLeave(this)">나가기</a>
					</div>
				</c:forEach>
				</div>
			</div>
			<div class="chatContainer">
				<input type="hidden" id="nowChatRoomId" value="${newChat.chatRoomId}">
				<input type="hidden" id="nowUserId" value="${newChat.userId}">
				<input type="hidden" id="nowUsername" value="${newChat.username}">
				
				<div class="chatPerson" id="chatPerson">${newChat.username}</div>
				<hr>
				<div class="chatContent" id="chatContent"></div>
				<hr>
				<div class="chatMessageInputContainer">
					<input class="chatMessageInput" type="text" id="chatMessage">
					<button class="chatMessageInputBtn" type="button" id="chatInput">전송</button>
				</div>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/view/business/layout/footer.jsp" %>