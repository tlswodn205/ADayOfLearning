package com.tencoding.ADayOfLearning.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tencoding.ADayOfLearning.dto.request.ChatMessageRequestDto;
import com.tencoding.ADayOfLearning.service.ChatService;

@Controller
@RequestMapping("/chat")
public class ChatController{
	
	@Autowired
	ChatService chatService;
	
	@Autowired
	HttpSession session;
	
	/**
	 * 기존의 채팅 내용 리스트 조회
	 * @param chatRoomId
	 * @return
	 */
	@GetMapping("/roomId")
	public @ResponseBody List<ChatMessageRequestDto> chatRoom(int chatRoomId){
		
		List<ChatMessageRequestDto> chat = chatService.findByChatRoomId(chatRoomId);
		return chat;
	}
	
}
