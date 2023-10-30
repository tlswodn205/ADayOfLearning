package com.tencoding.ADayOfLearning.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tencoding.ADayOfLearning.dto.request.ChatMessageRequestDto;
import com.tencoding.ADayOfLearning.service.ChatRoomService;
import com.tencoding.ADayOfLearning.service.ChatRoomUserService;
import com.tencoding.ADayOfLearning.service.ChatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chat")
public class ChatController{
	
	@Autowired
	ChatService chatService;
	@Autowired
	ChatRoomService chatRoomService;
	@Autowired
	ChatRoomUserService chatRoomUserService;
	
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
	
	@PostMapping("/insert")
	public @ResponseBody ResponseEntity<?> insert(@RequestBody ChatMessageRequestDto chatMessageRequestDto) {
    	System.out.println("/insert");
    	System.out.println(chatMessageRequestDto);
    	// 첫 대화의 경우 새로운 chatRoom 생성
    	try {
    		if(chatMessageRequestDto.getChatRoomId() < 1) {
    			log.info("chatRoomId < 1");
    			int chatRoomId = chatRoomService.insert(chatMessageRequestDto.getSendUserId(), chatMessageRequestDto.getReceiveUserId());
    			chatMessageRequestDto.setChatRoomId(chatRoomId);
    		} else {
    			// 한쪽만 대화방에서 나갔을 경우를 대비
    			chatRoomUserService.chatRoomUserCheck(chatMessageRequestDto);
    		}
    		// user1, user2가 chatroomuser에 있는지 확인 후 insert
    		chatService.insertChat(chatMessageRequestDto);
		} catch (Exception e) {
			log.info("오류 발생 : {}", e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    	return ResponseEntity.ok().build();
	}
	
}
