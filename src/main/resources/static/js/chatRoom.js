let stomp = null;

// 페이지 초기 설정 시작
let chatInit = {
    version: 1,
    init: function () {
        $(document).ready(() => {
            this.ready();
        });
    },
    // 랜더링 완료 후 시작
    ready: function () {
        this.chatAlarm();

        if ($('#nowChatRoomId').val() !== '') {
            chatInit.chatRoomCreated();
        }
        $('#chatMessage').keypress((key) => {
            this.messageKeyPress(key);
        });
        $('#chatInput').click(() => {
            this.chatMessageInsert();
        });
    },
    // 새로운 채팅 알림 기능
    chatAlarm: function () {
        let sockJS = new SockJS('/stomp/chat');
        let stompAlarm = Stomp.over(sockJS);
        stompAlarm.debug = null;
        stompAlarm.connect({}, function () {
            console.log('alarm Connection');
            stompAlarm.subscribe('/sub/chat/user/' + $('#userId').val(), function (chat) {
                let content = JSON.parse(chat.body);
                let sendUserId = $("#chatUserId[value='" + content.sendUserId + "']");
                if (sendUserId.length < 1) {
                    // 최초 보내기
                    chatRoomAppend(content);
                } else {
                    let Message = sendUserId.parent().find('#newMessage');
                    Message.text(parseInt(Message.text()) + 1);
                    sendUserId.parent().find('#newMessage').show();
                }
            });
        });
    },
    // 새로운 채팅방 생성
    chatRoomCreated: function () {
        let chatRoomId = $('#nowChatRoomId').val();
        let userId = $('#nowUserId').val();
        stompConnect(chatRoomId);
        chatList(chatRoomId, userId);
    },
    // 채팅 내용 입력창에서 enter 인식
    messageKeyPress: function (key) {
        if (key.keyCode == 13) {
            this.chatMessageInsert();
        }
    },
    // 채팅 메세지 전송
    chatMessageInsert: function () {
        if (stomp == null) {
            return;
        }
        let chatMessage = $('#chatMessage').val();
        if (chatMessage === '' || chatMessage === null) {
            console.log('채팅 내용 없음');
            return;
        }
		chatMessage = chatMessage.replace(/</gi, '&lt');
		chatMessage = chatMessage.replace(/>/gi, '&gt');
        let sendData = {
            chatRoomId: $('#nowChatRoomId').val(),
            sendUserId: $('#userId').val(),
            sendUsername: $('#username').val(),
            receiveUserId: $('#nowUserId').val(),
            receiveUsername: $('#nowUsername').val(),
            message: chatMessage,
        };

        $.ajax({
            url: '/chat/insert',
            method: 'post',
            contentType: 'application/json',
            data: JSON.stringify(sendData),
            success: function (response) {
                console.log('insert 성공: ', response);
                sendData.createdAt = response;
                stomp.send('/pub/chat/message', {}, JSON.stringify(sendData));
                $('#chatMessage').val('');
            },
            error: function (error) {
                console.log('insert 실패: ', error);
            },
        });
    },
}; // 페이지 초기 설정 끝

// 채팅방 입장 (onclick event)
function chatRoom(event) {
    if (stomp != null && stomp.connected) {
        stomp.disconnect();
    }
    let chatRoomId = $(event).parent().find('#chatRoomId');
    let chatUserId = $(event).parent().find('#chatUserId');
    let chatUsername = $(event).parent().find('#chatUsername');
    $(event).parent().find('#newMessage').text('0');
    $(event).parent().find('#newMessage').hide();

    nowChatInit(chatRoomId.val(), chatUserId.val(), chatUsername.val(), 'flex');
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
        console.log('leave 성공');
        let nowChatRoomId = $('#nowChatRoomId');
        if (chatRoomId.val() === nowChatRoomId.val()) {
            stomp.disconnect();
            nowChatInit('', '', '', 'none');
        }
        $(event).parent().remove();
    } else {
        console.log('leave 실패');
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
        success: function (response) {
            // 채팅창 초기화 표시
            console.log('chatList 성공');
            // 채팅 히스토리 출력
            response.forEach(function (chat, index) {
                chatContentAppend(chat.username, chat.message, chat.createdAt);
            });
            $('#chatContent').scrollTop($('#chatContent')[0].scrollHeight);
        },
        error: function (error) {
            console.log('chatList 실패: ', error);
        },
    });
}

// stomp 통신 연결
function stompConnect(chatRoomId) {
    let sockJS = new SockJS('/stomp/chat');
    stomp = Stomp.over(sockJS);
    stomp.debug = null;

    stomp.connect({}, function () {
        console.log('chat Connection');

        // subscribe
        stomp.subscribe('/sub/chat/room/' + chatRoomId, function (chat) {
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
function chatContentAppend(username, message, createdAt) {
    if(message == null) {
		return;
	} else if(username === $("#username").val()){
    // 내가 한 대화
		messageSend("chatMy", username, message, createdAt);
	} else {
	// 상대가 한 대화
		messageSend("chatOther", username, message, createdAt);
    }
}
	
// 대화창에 메세지 출력
function messageSend(chatClass, username, message, createdAt) {
	let str = '<div class="' + chatClass + '">';
	str += '<div class="chatUsername">' + username + '</div>';
	str += '<div class="chat">';
	str += '<div class="chatMessage">' + message + '</div>';
	str += '<div class="chatMessageTime">'+ createdAt + '</div>';
	str += '</div>';
	str += '</div>';
	$("#chatContent").append(str);
}

// 대화창 초기화
function nowChatInit(chatRoomId, chatUserId, chatUsername, chatMessageDisplay) {
    $('#chatMessage').val('');
    $('#chatPerson').text(chatUsername);
    $('#chatContent').empty();
    $('#nowChatRoomId').val(chatRoomId);
    $('#nowUserId').val(chatUserId);
    $('#nowUsername').val(chatUsername);
    $('#chatMessageInputContainer').css('display', chatMessageDisplay);
}

// 채팅방 리스트에 목록 하나 추가
function chatRoomAppend(content) {
    let html = '';
    html += '<div class="chatRoom">';
    html += '<input type="hidden" id="chatRoomId" value="' + content.chatRoomId + '">';
    html += '<input type="hidden" id="chatUserId" value="' + content.sendUserId + '">';
    html += '<input type="hidden" id="chatUsername" value="' + content.sendUsername + '">';
    html += '<a class="chatUsername" onclick="chatRoom(this)">' + content.sendUsername + '</a>';
    html += '<span class="newMessage" id="newMessage">1</span>';
    html += '<a class="chatDelete" id="chatLeave" onclick="chatLeave(this)">나가기</a>';
    html += '</div>';

    $('#chatRoomList').append(html);
}

chatInit.init();
