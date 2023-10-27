package com.tencoding.ADayOfLearning.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tencoding.ADayOfLearning.dto.response.ChatRoomResponseDto;
import com.tencoding.ADayOfLearning.service.ChatRoomService;

@Controller
@RequestMapping("/chatRoom")
public class ChatRoomController {

	@Autowired
	ChatRoomService chatRoomService;
	
	@Autowired
	HttpSession session;
	
	/**
	 * 채팅방 목록 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/test")
	public String chatTest(Model model) {
		int userId = 1;
		List<ChatRoomResponseDto> chatRoomList = chatRoomService.findByUserId(userId);
		model.addAttribute("chatRoomList", chatRoomList);
		return "chat/chatRoom";
	}
	
	@GetMapping("/insert")
	public String chatInsert() {
		chatRoomService.insert();
		return "chat/chatRoom";
	}
}
