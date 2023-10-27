package com.tencoding.ADayOfLearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.tencoding.ADayOfLearning.dto.request.ChatMessageRequestDto;
import com.tencoding.ADayOfLearning.dto.request.ChatRoomEnterRequestDto;
import com.tencoding.ADayOfLearning.service.ChatRoomService;
import com.tencoding.ADayOfLearning.service.ChatRoomUserService;
import com.tencoding.ADayOfLearning.service.ChatService;

@Controller
public class ChatStompController {
	/**
	 * 채팅 내용 전달 관련 컨트롤러
	 */
	@Autowired
    private SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private ChatRoomService chatRoomService;
	
	@Autowired
	private ChatRoomUserService chatRoomUserService;

    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatRoomEnterRequestDto message){
    	// user1 user2 정보 필요.
    	System.out.println("/chat/enter");
    	System.out.println(message);
    	
    	
    	// 새로운 chatRoom 생성
    	if(message.getChatRoomId() < 1) {
    		int chatRoomId = chatRoomService.insert(message.getUsername(), message.getChatUsername());
    		
    	} else {
    		chatRoomUserService.chatRoomEnter(message.getUsername(), message.getChatUsername());
    	}
    	// user1, user2가 chatroomuser에 있는지 확인 후 insert
    	
        template.convertAndSend("/sub/chat/room/" + message.getChatRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageRequestDto message){
    	System.out.println("/chat/message");
    	System.out.println(message);
    	chatService.insertChat(message);
        template.convertAndSend("/sub/chat/room/" + message.getChatRoomId(), message);
    }

}
