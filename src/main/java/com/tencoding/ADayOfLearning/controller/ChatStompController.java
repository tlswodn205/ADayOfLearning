package com.tencoding.ADayOfLearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.tencoding.ADayOfLearning.dto.request.ChatMessageRequestDto;
import com.tencoding.ADayOfLearning.dto.request.NewChatRequestDto;

@Controller
public class ChatStompController {
	/**
	 * 채팅 내용 전달 관련 컨트롤러
	 */
	@Autowired
    private SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
	
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping(value = "/chat/enter")
    public void enter(NewChatRequestDto message){
    	System.out.println("/chat/enter");
    	System.out.println(message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageRequestDto message){
    	System.out.println("/chat/message");
    	System.out.println(message);
    	// 첫 대화의 경우 새로운 chatRoom 생성
    	template.convertAndSend("/sub/chat/user/" + message.getReceiveUserId(), message);
    	template.convertAndSend("/sub/chat/room/" + message.getChatRoomId(), message);    	
    }
    
}
