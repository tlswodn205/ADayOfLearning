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
			sendUserId: $("#userId").val(),
			sendUsername: $("#username").val(),
			receiveUserId: $("#nowUserId").val(),
			receiveUsername: $("#nowUsername").val(),
			message: chatMessage.val()
		};
		
		$.ajax({
			url: '/chat/insert',
			method: 'post',
			contentType: 'application/json',
			data: JSON.stringify(sendData),
			success: function(response) {
				console.log('성공: ', response);
				stomp.send('/pub/chat/message', {}, JSON.stringify(sendData))
		        chatMessage.val('');
			},
			error: function(error) {
				console.log('실패: ', error);
			}
		});
	});
	
	if($("#nowChatRoomId").val() !== '') {
		console.log("새로운 채팅 - ");
		stompConnect($("#nowChatRoomId").val());
	}
	
}) // end of document ready

let stomp = null;
// 이미 존재하는 채팅방 입장
function chatRoom(event) {
	let chatRoomId = $(event).parent().find("#chatRoomId");
	let chatUserId = $(event).parent().find("#chatUserId");
	let chatUsername = $(event).parent().find("#chatUsername");
	$.ajax({
		url: '/chat/roomId',
		method: 'get',
		dataType: 'json',
		data: { chatRoomId: chatRoomId.val() },
		success: function(response) {
			// 채팅창 초기화 표시
			console.log('성공: ', response);
			$("#chatMessage").val('');
			$("#chatPerson").text(chatUsername.text());
			$("#chatContent").empty();
			$("#nowChatRoomId").val(chatRoomId.val());
			$("#nowUserId").val(chatUserId.val());
			$("#nowUsername").val(chatUsername.text());
			// 채팅 히스토리 출력
			response.forEach(function (chat, index) {
				username = chat.sendUsername;
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
			$("#chatContent").scrollTop($("#chatContent")[0].scrollHeight);
		},
		error: function(error) {
			console.log('실패: ', error);
		}
	});
	
	stompConnect(chatRoomId.val());
}

function stompConnect(chatRoomId) {
	// 실시간 채팅을 위한 통신
	let sockJS = new SockJS("/stomp/chat");
	if(stomp != null && stomp.connected) {
		stomp.disconnect();
	}
	stomp = Stomp.over(sockJS);
	stomp.debug = null;
    //2. connection이 맺어지면 실행
    stomp.connect({}, function (){
		console.log("STOMP Connection " + chatRoomId);

		//4. subscribe(path, callback)으로 메세지를 받을 수 있음
		stomp.subscribe("/sub/chat/room/" + chatRoomId, function (chat) {
			console.log('subscribe');
			let content = JSON.parse(chat.body);
			if(chatRoomId === 0) {
				// 새로운 채팅일 경우 nowChatRoomId 갱신, 재연결, 재전송 필요!!!
			}
			let stompUsername = content.sendUsername;
			let stompMessage = content.message;

	        if(stompMessage === null) {
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
			$("#chatContent").scrollTop($("#chatContent")[0].scrollHeight);
		});

		//3. send(path, header, message)로 메세지를 보낼 수 있음
		let sendData = {
			chatRoomId: chatRoomId
		};
		stomp.send('/pub/chat/enter', {}, JSON.stringify(sendData));
    });
}

async function chatLeave(event) {
	// 다시 물어보는 작업 필요
	let chatRoomId = $(event).parent().find("#chatRoomId");
	
	let response = await fetch('/chat/leave',{
		method: 'put',
		body: JSON.stringify({
			chatRoomId : chatRoomId.val()
		}),
		headers: {
			"Content-Type":"application/json; charset=utf-8"
		}
	});
	
	if(response.status == 200) {
		console.log('leave 성공');
		let nowChatRoomId = $("#nowChatRoomId");
		if(chatRoomId.val() === nowChatRoomId.val()) {
			stomp.disconnect();
			nowChatEmpty();
		}
		$(event).parent().remove();
	} else {
		console.log('leave 실패');
	}
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

function nowChatEmpty() {
	$("#nowChatRoomId").val('');
	$("#nowUserId").val('');
	$("#nowUsername").val('');
	$("#chatPerson").text('');
	$("#chatContent").text('');
}