<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<link rel="stylesheet" href="/css/chatRoom.css">
<main>
	<div id="chat">
		<div class="chatContainer">
			<div class="chatRoomListContainer">
				<div class="myPerson">
					내정보 : ${person.name}
				</div>
				<hr>
				<input type="hidden" id="username" value="${person.name}">
				<input type="hidden" id="userId" value="${principal.userId}">
				<div class="chatRoomList" id="chatRoomList"></div>
			</div>
			
			<div class="partition"></div>
			
			<div class="chatContentContainer">
				<input type="hidden" id="nowChatRoomId" value="${newChat.chatRoomId}">
				<input type="hidden" id="nowUserId" value="${newChat.userId}">
				<input type="hidden" id="nowUsername">
				
				<div class="chatPerson" id="chatPerson"></div>
				<hr>
				<div class="chatContent" id="chatContent"></div>
				<hr>
				<div class="chatMessageInputContainer" id="chatMessageInputContainer">
					<textarea class="chatMessageInput" id="chatMessage"></textarea>
					<button class="chatMessageInputBtn" id="chatInput">전 송</button>
				</div>
			</div>
		</div>
	</div>
</main>
<script>
	let chatRoomList = ${chatRoomList};
</script>
<script src="/js/chatRoom.js"></script>