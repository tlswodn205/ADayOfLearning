/*
principal - user1
user1이 user2에 대화 시작 

체팅룸id 생성 (3).
chat_room_user에 user1, user2 인서트


체팅 리스트
chatRoomId
chatRoomUsername

채팅 내용
nowChatUsername
nowChatUserId
nowChatMessage

대화 입력

chatroom

나 자신
userId
userName

채팅 상대
*/

$(document).ready(function () {
	$("#chatMessage").keypress((key) => {
		if(key.keyCode == 13) {
			$("#chatInput").click();
		}
	})
	
	// 대화 전송
	$("#chatInput").click(() => {
		if(stomp == null) {
			return;
		}
		let chatMessage = $("#chatMessage");
		if(chatMessage.val() === "" || chatMessage.val() === null) {
			console.log("채팅 내용 없음");
			return;
		}
		
		let sendData = {
			chatRoomId: $("#nowChatRoomId").val(),
			sendUserId: $("#username").val(),
			sendUsername: $("#userId").val(),
			receiveUserId: $("#nowUserId").val(),
			receiveUsername: $("#nowUsername").val(),
			message: chatMessage.val()
		};
		stomp.send('/pub/chat/message', {}, JSON.stringify(sendData))
        chatMessage.val('');
	});
}) // end of document ready

let stomp = null;
// 이미 존재하는 채팅방 입장
function chatRoom(event) {
	let chatRoomId = $(event).find("#chatRoomId");
	let chatUserId = $(event).find("#chatUserId");
	let chatUsername = $(event).find("#chatUsername");
	$.ajax({
		url: '/chat/roomId',
		method: 'get',
		dataType: 'json',
		data: { chatRoomId: chatRoomId.val() },
		success: function(response) {
			// 채팅창 초기화 표시
			console.log('성공: ', response);
			$("#chatMessage").val('');
			$("#chatPerson").text(chatUsername.text() + "와의 대화 내용");
			$("#chatContent").empty();
			// 채팅 히스토리 출력
			response.forEach(function (chat, index) {
				username = chat.username;
				message = chat.message;
		        if(message == null) {
					return;
				}
		        // 내가 한 대화
				else if(username === $("#username").val()){
					messageMySend(username, message);
				}
				// 상대가 한 대화
				else {
					messageOtherSend(username, message);
		        }
			});
		},
		error: function(error) {
			console.log('실패: ', error);
		}
	});
	
	// 실시간 채팅을 위한 통신
	let sockJS = new SockJS("/stomp/chat");
	if(stomp != null && stomp.connected) {
		stomp.disconnect();
	}
	stomp = Stomp.over(sockJS);
	stomp.debug = null;
    //2. connection이 맺어지면 실행
    stomp.connect({}, function (){
		console.log("STOMP Connection");

		//4. subscribe(path, callback)으로 메세지를 받을 수 있음
		stomp.subscribe("/sub/chat/room/" + chatRoomId.val(), function (chat) {
			console.log('subscribe');
			let content = JSON.parse(chat.body);
			let stompUsername = content.sendUsername;
			let stompMessage = content.message;

	        if(stompMessage == null) {
				return;
			}
	        // 내가 한 대화
			else if(stompUsername === $("#username").val()){
				messageMySend(stompUsername, stompMessage);
			}
			// 상대가 한 대화
			else {
				messageOtherSend(stompUsername, stompMessage);
	        }

		});

		//3. send(path, header, message)로 메세지를 보낼 수 있음
		let sendData = {
			chatRoomId: chatRoomId.val()
		};
		stomp.send('/pub/chat/enter', {}, JSON.stringify(sendData));
    });
}

function messageMySend(username, message) {
	let str = '<div class="chatMy">';
	str += '<div class="chatUsername">';
	str += '<b>' + username + '</b>';
	str += '</div>';
	str += '<div class="chatMessage">';
	str += message;
	str += '</div>';
	str += '</div>';
	$("#chatContent").append(str);
}
function messageOtherSend(username, message) {
	let str = '<div class="chatOther">';
	str += '<div class="chatUsername">';
	str += '<b>' + username + '</b>';
	str += '</div>';
	str += '<div class="chatMessage">';
	str += message;
	str += '</div>';
	str += '</div>';
	$("#chatContent").append(str);
}