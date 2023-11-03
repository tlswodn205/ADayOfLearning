package com.tencoding.ADayOfLearning.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tencoding.ADayOfLearning.dto.request.NewChatRequestDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessMainUserDataResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ChatRoomResponseDto;
import com.tencoding.ADayOfLearning.dto.request.BusinessUserRequestDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessUserDetailResponseDto;
import com.tencoding.ADayOfLearning.repository.model.User;
import com.tencoding.ADayOfLearning.service.BusinessService;
import com.tencoding.ADayOfLearning.service.ChatRoomService;
import com.tencoding.ADayOfLearning.service.UserService;
import com.tencoding.ADayOfLearning.util.Define;

@Controller
@RequestMapping("/business")
public class BusinessController {

	@Autowired
	BusinessService businessService;
	@Autowired
	UserService userService;
	@Autowired
	ChatRoomService chatRoomService;
	
	
	@Autowired
	HttpSession session;
	
	//main start 
	
	@GetMapping("")
	public String getMain(Model model) {	
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		BusinessMainUserDataResponseDto userData = businessService.findUserData(1);
		model.addAttribute("userData", userData);
		return "/business/main";
	}
	
	@GetMapping("/chatRoom")
	public String businessChatRoom(NewChatRequestDto newChatRequestDto, Model model) {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		if(principal == null) {
			return "redirect:/user/signIn";
		}
		if(newChatRequestDto.getUserId() > 0) {
			// 새로운 채팅방 생성 - chatRoom, chatRoomUser 데이터 생성
			int chatRoomId = chatRoomService.insert(principal.getUserId(), newChatRequestDto.getUserId());
			
			// 해당 채팅방 연결을 위한 데이터 세팅
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
	
	//main end
	
	//user start
	

	@GetMapping("/userDetail")
	public String getUserDetail(Model model) {
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		BusinessUserDetailResponseDto businessUserDetailRequestDto =  businessService.findBusinessByUserID(user);
		model.addAttribute("businessUserData", businessUserDetailRequestDto);
		return "/business/user/userDetail";
	}
	
	@PostMapping("/businessUpdate")
	public String businessUpdate(BusinessUserRequestDto businessUserRequestDto) {
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		businessService.updateBusinessUserData(businessUserRequestDto, user.getUserId());
		return "redirect:/business/userDetail";
	}
	
	//user end
	
}
