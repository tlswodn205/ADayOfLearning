$(document).ready(function () {
	
	let sockJS = new SockJS("/stomp/chat");
	let stompAlarm = Stomp.over(sockJS);
	stompAlarm.debug = null;
    stompAlarm.connect({}, function (){
		console.log("STOMP Connection alarm " + $("#userId").val());
		stompAlarm.subscribe("/sub/chat/user/" + $("#userId").val(), function (chat) {
			console.log('알람');
			let content = JSON.parse(chat.body);
			console.log(content);
			let sendUserId = $("#chatUserId[value='" + content.sendUserId + "']");
			let nowUserId = $("#nowUserId");
			console.log(nowUserId.val());
			if(sendUserId.length < 1) {
				// 최초 보내기
				chatRoomAppend(content);
			} else if(nowUserId.val() !== content.sendUserId.toString()){
				sendUserId.parent().find("#newMessage").show();
			}
		});
	});
	
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
		console.log("새로운 채팅방 생성");
		let chatRoomId = $("#nowChatRoomId").val();
		let userId = $("#nowUserId").val();
		stompConnect(chatRoomId);
		chatList(chatRoomId, userId);
	}
	
}) // end of document ready

let stomp = null;
// 이미 존재하는 채팅방 입장
function chatRoom(event) {
	if(stomp != null && stomp.connected) {
		stomp.disconnect();
	}
	let chatRoomId = $(event).parent().find("#chatRoomId");
	let chatUserId = $(event).parent().find("#chatUserId");
	let chatUsername = $(event).parent().find("#chatUsername");
	$(event).parent().find("#newMessage").hide();
	
	nowChatInit(chatRoomId.val(), chatUserId.val(), chatUsername.val());
	chatList(chatRoomId.val(), chatUserId.val());
	stompConnect(chatRoomId.val());
}

function stompConnect(chatRoomId) {
	// stomp 통신 연결
	let sockJS = new SockJS("/stomp/chat");
	stomp = Stomp.over(sockJS);
	stomp.debug = null;
    //2. connection이 맺어지면 실행
    stomp.connect({}, function (){
		console.log("STOMP Connection " + chatRoomId);

		//4. subscribe(path, callback)으로 메세지를 받을 수 있음
		stomp.subscribe("/sub/chat/room/" + chatRoomId, function (chat) {
			console.log('subscribe');
			let content = JSON.parse(chat.body);
			chatContentAppend(content.sendUsername, content.message);
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
			nowChatInit('', '', '');
		}
		$(event).parent().remove();
	} else {
		console.log('leave 실패');
	}
}

function chatList(chatRoomId, userId) {
	$.ajax({
		url: '/chat/roomId',
		method: 'get',
		dataType: 'json',
		data: {
			chatRoomId: chatRoomId,
			userId: userId
		},
		success: function(response) {
			// 채팅창 초기화 표시
			console.log('성공: ', response);
			// 채팅 히스토리 출력
			response.forEach(function (chat, index) {
				chatContentAppend(chat.username, chat.message);
			});
			$("#chatContent").scrollTop($("#chatContent")[0].scrollHeight);
		},
		error: function(error) {
			console.log('실패: ', error);
		}
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

function nowChatInit(chatRoomId, chatUserId, chatUsername) {
	$("#chatMessage").val('');
	$("#chatPerson").text(chatUsername);
	$("#chatContent").empty();
	$("#nowChatRoomId").val(chatRoomId);
	$("#nowUserId").val(chatUserId);
	$("#nowUsername").val(chatUsername);
}

function chatRoomAppend(content) {
	let html = '';
	html += '<div class="chatRoom">';
	html += '<input type="hidden" id="chatRoomId" value="' + content.chatRoomId + '">';
	html += '<input type="hidden" id="chatUserId" value="' + content.sendUserId + '">';
	html += '<input type="hidden" id="chatUsername" value="' + content.sendUsername + '">';
	html += '<a class="chatUsername" onclick="chatRoom(this)">' + content.sendUsername + '</a>';
	html += '<span class="newMessage" id="newMessage">new</span>';
	html += '<a class="chatDelete" id="chatLeave" onclick="chatLeave(this)">나가기</a>';
	html += '</div>';
	
	$("#chatRoomList").append(html);
};

function chatContentAppend(username, message) {
    if(message == null) {
		return;
	} else if(username === $("#username").val()){
    // 내가 한 대화
		messageMySend(username, message);
	} else {
	// 상대가 한 대화
		messageOtherSend(username, message);
    }
}
