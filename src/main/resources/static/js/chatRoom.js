let stomp = null;

// 페이지 초기 설정 시작
let chatInit = {
	version: 1,
	init: function() {
		$(document).ready(() => { this.ready(); });
		// 대화 입력창 엔터키 이벤트
		$('#chatMessage').keypress((key) => { this.messageKeyPress(key); });
		// 대화 전송 버튼 클릭 이벤트
		$('#chatInput').click(() => { this.chatMessageInsert(); });
	},
	// 랜더링 완료 후 시작
	ready: function() {
		// 채팅 알림 기능 세팅
		this.chatAlarm();
		// 채팅방 리스트 세팅
		this.chatRoomList();
		// 문의하기로 새로운 채팅방 생성
		if ($('#nowChatRoomId').val() !== '') {
			this.inquiryInit();
		}
	},
	// 채팅 알림 기능
	chatAlarm: function() {
		let sockJS = new SockJS('/stomp/chat');
		let stompAlarm = Stomp.over(sockJS);
		stompAlarm.debug = null;
		stompAlarm.connect({}, function() {
			console.log('alarm Connection');
			stompAlarm.subscribe('/sub/chat/user/' + $('#userId').val(), function(chat) {
				console.log(chat);
				let content = JSON.parse(chat.body);
				let sendUserId = $("#chatUserId[value='" + content.sendUserId + "']");
				if (sendUserId.length < 1) {
					// 최초 보내기
					chatRoomAppend(content.chatRoomId, content.sendUserId, content.sendUsername, 1);
				} else {
					let Message = sendUserId.parent().find('#newMessage');
					Message.text(parseInt(Message.text()) + 1);
					sendUserId.parent().find('#newMessage').show();
				}
			});
		});
	},
	inquiryInit: function() {
		$('.chatRoom[value="' + $('#nowChatRoomId').val() + '"]').click();
	},
	chatRoomList: function() {
		chatRoomList.forEach((chatRoom, index) => {
			chatRoomAppend(chatRoom.chatRoomId, chatRoom.userId, chatRoom.name, chatRoom.viewCount);
		})
	},
	// 채팅 내용 입력창에서 enter 인식
	messageKeyPress: function(key) {
		if (key.keyCode == 13 && !key.shiftKey) {
			this.chatMessageInsert();
		}
	},
	// 채팅 메세지 전송
	chatMessageInsert: async function() {
		if (stomp == null) {
			return;
		}
		let chatMessage = $('#chatMessage').val();
		if (chatMessage.trim() === '' || chatMessage.trim() === null) {
			console.error('채팅 내용 없음');
			return;
		}
		let sendData = {
			chatRoomId: $('#nowChatRoomId').val(),
			sendUserId: $('#userId').val(),
			sendUsername: $('#username').val(),
			receiveUserId: $('#nowUserId').val(),
			receiveUsername: $('#chatPerson').text(),
			message: chatMessage,
		};
		console.log(sendData)
		await fetch('/chat/insert', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(sendData),
		})
		.then(response => {
			if (!response.ok) {
				throw new Error('insert 실패');
			}
			return response.json();
		})
		.then(data => {
			sendData.createdAt = data.createdAt;
			stomp.send('/pub/chat/message', {}, JSON.stringify(sendData));
			$('#chatMessage').val('');
		})
		.catch(error => {
			console.error('insert 실패: ', error);
		});
	},
}; // 페이지 초기 설정 끝

// 채팅방 입장 (onclick event)
function chatRoom(element) {
	if (stomp != null && stomp.connected) {
		stomp.disconnect();
	}
	let chatRoomId = $(element).parent().find('#chatRoomId');
	let chatUserId = $(element).parent().find('#chatUserId');
	let chatName = $(element).parent().find('#chatName');
	$('.clicked').removeClass('clicked');
	$(element).addClass('clicked');
	$(element).parent().find('#newMessage').text('0');
	$(element).parent().find('#newMessage').hide();

	nowChatInit(chatRoomId.val(), chatUserId.val(), chatName.val(), 'flex');
	chatList(chatRoomId.val(), chatUserId.val());
	stompConnect(chatRoomId.val());
}

// 채팅방 나가기 (onclick event)
async function chatLeave(event) {
	// 다시 물어보는 작업 필요
	let chatRoomId = $(event).parent().find('#chatRoomId');

	let response = await fetch('/chat/leave', {
		method: 'put',
		body: JSON.stringify({
			chatRoomId: chatRoomId.val(),
		}),
		headers: {
			'Content-Type': 'application/json; charset=utf-8',
		},
	});

	if (response.status == 200) {
		let nowChatRoomId = $('#nowChatRoomId');
		if (chatRoomId.val() === nowChatRoomId.val()) {
			stomp.disconnect();
			nowChatInit('', '', '', 'none');
		}
		$(event).parent().remove();
	} else {
		console.error('leave 실패');
	}
}

// 채팅방 입장 후 대화 목록 조회
function chatList(chatRoomId, userId) {
	$.ajax({
		url: '/chat/roomId',
		method: 'get',
		dataType: 'json',
		data: {
			chatRoomId: chatRoomId,
			userId: userId,
		},
		success: function(response) {
			// 채팅 히스토리 출력
			response.forEach(function(chat, index) {
				chatContentAppend(chat.name, chat.message, chat.createdAt);
			});
			$('#chatContent').scrollTop($('#chatContent')[0].scrollHeight);
		},
		error: function(error) {
			console.error('chatList 실패: ', error);
		},
	});
}

// stomp 통신 연결
function stompConnect(chatRoomId) {
	let sockJS = new SockJS('/stomp/chat');
	stomp = Stomp.over(sockJS);
	stomp.debug = null;

	stomp.connect({}, function() {
		console.log('chat Connection');

		// subscribe
		stomp.subscribe('/sub/chat/room/' + chatRoomId, function(chat) {
			console.log('subscribe');
			let content = JSON.parse(chat.body);
			chatContentAppend(content.sendUsername, content.message, content.createdAt);
			$('#chatContent').scrollTop($('#chatContent')[0].scrollHeight);
		});

		let sendData = {
			chatRoomId: chatRoomId,
		};
		stomp.send('/pub/chat/enter', {}, JSON.stringify(sendData));
	});
}

// 화면 변경 관련 함수들
// 대화창에 대화 출력하기
function chatContentAppend(name, message, createdAt) {
	if (message == null) {
		return;
	} else if (name === $("#username").val()) {
		// 내가 한 대화
		messageSend("chatMy", name, message, createdAt);
	} else {
		// 상대가 한 대화
		messageSend("chatOther", name, message, createdAt);
	}
}

// 대화창에 메세지 출력
function messageSend(chatClass, username, message, createdAt) {
	let chatContainer =
	$('<div>', { class: chatClass }).append(
		$('<div>', { class: 'chatUsername', text: username }),
		$('<div>', { class: 'chat' }).append(
			$('<div>', { class: 'chatMessage', text: message }),
			$('<div>', { class: 'chatMessageTime', text: createdAt })
		)
	);
	$("#chatContent").append(chatContainer);
}

// 대화창 초기화
function nowChatInit(chatRoomId, chatUserId, chatName, chatMessageDisplay) {
	$('#chatMessage').val('');
	$('#chatPerson').text(chatName);
	$('#chatContent').empty();
	$('#nowChatRoomId').val(chatRoomId);
	$('#nowUserId').val(chatUserId);
	$('#nowName').val(chatName);
	$('#chatMessageInputContainer').css('display', chatMessageDisplay);
}

// 채팅방 리스트에 목록 하나 추가
function chatRoomAppend(chatRoomId, userId, name, viewCount) {
	let chatRoomContainer =
	$('<div>', { class: 'chatRoomContainer' }).append(
		$('<input>', { type: 'hidden', id: 'chatRoomId', value: chatRoomId }),
		$('<input>', { type: 'hidden', id: 'chatUserId', value: userId }),
		$('<input>', { type: 'hidden', id: 'chatName', value: name }),
		/*$('<div>', { class: 'chatRoom', click: function() { chatRoom(this); } }).append(*/
		$('<div>', { class: 'chatRoom', value: chatRoomId, click: function() { chatRoom(this); } }).append(
			$('<a>', { class: 'chatTitle', text: name }),
			$('<span>', { class: 'newMessage', id: 'newMessage', text: viewCount
						, style: viewCount > 0 ? '' : 'display:none'
			})
		),
		$('<a>', { class: 'chatLeave', id: 'chatLeave', click: function() { chatLeave(this); }, text: '나가기' })
	);

	$('#chatRoomList').append(chatRoomContainer);
}

chatInit.init();
