package com.tencoding.ADayOfLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.repository.interfaces.ChatRoomUserRepository;

@Service
public class ChatRoomUserService {

	@Autowired
	ChatRoomUserRepository chatRoomUserRepository;
}
