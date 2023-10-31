package com.tencoding.ADayOfLearning.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tencoding.ADayOfLearning.dto.request.ChatMessageRequestDto;
import com.tencoding.ADayOfLearning.dto.request.ChatRoomEnterRequestDto;
import com.tencoding.ADayOfLearning.dto.response.ChatRoomResponseDto;
import com.tencoding.ADayOfLearning.repository.model.User;
import com.tencoding.ADayOfLearning.service.ChatRoomService;
import com.tencoding.ADayOfLearning.service.ChatRoomUserService;
import com.tencoding.ADayOfLearning.service.ChatService;
import com.tencoding.ADayOfLearning.service.UserService;
import com.tencoding.ADayOfLearning.util.Define;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chat")
public class ChatController{
	
	@Autowired
	UserService userService;
	@Autowired
	ChatService chatService;
	@Autowired
	ChatRoomService chatRoomService;
	@Autowired
	ChatRoomUserService chatRoomUserService;
	
	@Autowired
	HttpSession session;
	
	/**
	 * 채팅방 목록 리스트 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/room")
	public String chatRoomList(ChatRoomEnterRequestDto chatRoomEnterRequestDto, Model model) {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		if(principal == null) {
			return "redirect:/user/signIn";
		}
		log.info("/chat/room dto - {}", chatRoomEnterRequestDto);
		if(chatRoomEnterRequestDto.getUserId() > 0) {
			
			int chatRoomId = chatRoomService.insert(principal.getUserId(), chatRoomEnterRequestDto.getUserId());
			log.info("chatRoomId - {}", chatRoomId);
			chatService.insertEnter(chatRoomId, principal.getUserId());
			log.info("chat에 content null인 데이터 (입장여부) 확인");
			// dto 조절 필요
			chatRoomEnterRequestDto.setChatRoomId(chatRoomId);
			User chatUser = userService.findByUserId(chatRoomEnterRequestDto.getUserId());
			
			chatRoomEnterRequestDto = ChatRoomEnterRequestDto.builder()
														.chatRoomId(chatRoomId)
														.userId(chatRoomEnterRequestDto.getUserId())
														.username(chatUser.getUsername())
														.build();
			model.addAttribute("chatEnter" ,chatRoomEnterRequestDto);
		}
		
		List<ChatRoomResponseDto> chatRoomList = chatRoomService.findByUserId(principal.getUserId());
		log.info("chatRoomList : {}", chatRoomList);
		model.addAttribute("chatRoomList", chatRoomList);
		return "chat/chatRoom";
	}
	
	/**
	 * 기존의 채팅 내용 리스트 조회
	 * @param chatRoomId
	 * @return List<ChatMessageRequestDto>
	 */
	@GetMapping("/roomId")
	public @ResponseBody List<ChatMessageRequestDto> chatList(int chatRoomId){
		
		List<ChatMessageRequestDto> chat = chatService.findByChatRoomId(chatRoomId);
		return chat;
	}
	
	/**
	 * 채팅 전송할 내용 저장
	 * @param chatMessageRequestDto
	 * @return 
	 */
	@PostMapping("/insert")
	public @ResponseBody ResponseEntity<?> insert(@RequestBody ChatMessageRequestDto chatMessageRequestDto) {
    	log.info("/insert");
    	log.info("{}",chatMessageRequestDto);
    	
    	// user1, user2가 chatroomuser에 있는지 확인 후 insert
    	chatService.insertChat(chatMessageRequestDto);
    	return ResponseEntity.ok().build();
	}
	
}
