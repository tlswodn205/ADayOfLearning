package com.tencoding.ADayOfLearning.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tencoding.ADayOfLearning.dto.request.ChatMessageRequestDto;
import com.tencoding.ADayOfLearning.dto.request.NewChatRequestDto;
import com.tencoding.ADayOfLearning.dto.response.ChatMessageResponsoDto;
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
	public String chatRoomList(NewChatRequestDto newChatRequestDto, Model model) {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		if(principal == null) {
			return "redirect:/user/signIn";
		}
		log.info("/room - {}", newChatRequestDto);
		if(newChatRequestDto.getUserId() > 0) {
			// 새로운 채팅방 생성
			// chatRoom, chatRoomUser 데이터 생성
			int chatRoomId = chatRoomService.insert(principal.getUserId(), newChatRequestDto.getUserId());
			log.info("/room chatRoomId - {}", chatRoomId);
			
			// 해당 유저의 name 가져와 model에 담기
			User chatUser = userService.findByUserId(newChatRequestDto.getUserId());
			newChatRequestDto = NewChatRequestDto.builder()
												.chatRoomId(chatRoomId)
												.userId(newChatRequestDto.getUserId())
												.username(chatUser.getUsername())
												.build();
			model.addAttribute("newChat" ,newChatRequestDto);
		}
		
		List<ChatRoomResponseDto> chatRoomList = chatRoomService.findByUserId(principal.getUserId());
		model.addAttribute("chatRoomList", chatRoomList);
		return "chat/chatRoom";
	}
	
	/**
	 * 채팅 내용 히스토리 리스트 조회
	 * @param chatRoomId
	 * @return List<ChatMessageResponsoDto>
	 */
	@GetMapping("/roomId")
	public @ResponseBody List<ChatMessageResponsoDto> chatList(int chatRoomId){
		log.info("/roomId - {}", chatRoomId);
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		List<ChatMessageResponsoDto> chat = chatService.chatRoomEnter(chatRoomId, principal.getUserId());
		return chat;
	}
	
	/**
	 * 채팅 전송할 내용 저장
	 * @param chatMessageRequestDto
	 * @return ResponseEntity
	 */
	@PostMapping("/insert")
	public @ResponseBody ResponseEntity<?> insert(@RequestBody ChatMessageRequestDto chatMessageRequestDto) {
    	log.info("/insert - {}", chatMessageRequestDto);
    	
    	// user1, user2가 chatroomuser에 있는지 확인 후 insert
    	String createdAt = chatService.insertChat(chatMessageRequestDto);
    	
    	return ResponseEntity.ok().body(createdAt);
	}
	
	/**
	 * 채팅방 나가기
	 * @param chatLeaveRequestDto
	 * @return ResponseEntity
	 */
	@PutMapping("/leave")
	public @ResponseBody ResponseEntity<?> leave(@RequestBody Map<String, Integer> chatRoomId) {
		log.info("/leave - {}", chatRoomId);
		
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		chatRoomUserService.delete(chatRoomId.get("chatRoomId"), principal.getUserId());
		
		return ResponseEntity.ok().build();
	}
	
}
