$(document).ready(function () {
	$("#chatMessage").keypress((key) => {
		if(key.keyCode == 13) {
			$("#chatInput").click();
		}
	})
	
	$("#chatInput").click(() => {
		if(stomp == null) {
			return;
		}
		let chatMessage = $("#chatMessage");
		console.log(chatMessage.val());
		
		let sendData = {
			chatRoomId: $("#nowChatRoomId").val(),
			name: 'user1',
			message: chatMessage.val()
		};
		stomp.send('/pub/chat/message', {}, JSON.stringify(sendData))
        chatMessage.val('');
	});
}) // end of document ready

username = 'user1';

let stomp = null;
function chatRoom(event) {
	let chatRoomId = $(event).find("#chatRoomId");
	let chatUsername = $(event).find("#name");
	$("#chatMessage").val('');
	$.ajax({
		url: '/chat/roomId',
		method: 'get',
		dataType: 'json',
		data: { chatRoomId: chatRoomId.val() },
		success: function(response) {
			console.log('성공: ', response);
			$("#chatPerson").text(chatUsername.text() + "와의 대화 내용");
			$("#chatContent").empty();
			response.forEach(function (chat, index) {
				let html = "<br>" + chat.context;
				$("#chatContent").append(html);
				$("#nowChatRoomId").val(chatRoomId.val());
			});
		},
		error: function(error) {
			console.log('실패: ', error);
		}
	});
	
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
			console.log(chat);
			console.log(chat.body);
			let content = JSON.parse(chat.body);
			let chatRoomId = content.chatRoomId;
			let name = content.name;
			let message = content.message;

	        //로그인 한 클라이언트와 타 클라이언트를 분류하기 위함
			if(name === username){
				let str = "<div class='col-6'>";
				str += "<b>" + name + " : " + message + "</b>";
				str += "</div>";
				$("#chatContent").append(str);
			}
			else {
				var str = "<div class='col-6'>";
				str += "<b>" + name + " : " + message + "</b>";
				str += "</div>";
				$("#chatContent").append(str);
	        }

			$("#msgArea").append(str);
		});

		//3. send(path, header, message)로 메세지를 보낼 수 있음
		let sendData = {
			chatRoomId: chatRoomId.val(),
			name: chatUsername.text()
		};
		stomp.send('/pub/chat/enter', {}, JSON.stringify(sendData))
    });
    
}