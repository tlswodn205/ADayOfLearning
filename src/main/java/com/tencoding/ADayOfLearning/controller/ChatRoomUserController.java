package com.tencoding.ADayOfLearning.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tencoding.ADayOfLearning.service.ChatRoomUserService;

@Controller
@RequestMapping("/chatRoomUser")
public class ChatRoomUserController {

	@Autowired
	ChatRoomUserService chatRoomUserService;
	
	@Autowired
	HttpSession session;
}
