package com.tencoding.ADayOfLearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.tencoding.ADayOfLearning.dto.request.ChatMessageRequestDto;

@Controller
public class ChatStompController {
	/**
	 * 채팅 내용 전달 관련 컨트롤러
	 */
	@Autowired
    private SimpMessagingTemplate template; //특정 Broker로 메세지를 전달

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageRequestDto message){
    	System.out.println("/chat/enter");
    	System.out.println(message);
        message.setMessage(message.getName() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getChatRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageRequestDto message){
    	System.out.println("/chat/message");
    	System.out.println(message);
        template.convertAndSend("/sub/chat/room/" + message.getChatRoomId(), message);
    }

}
