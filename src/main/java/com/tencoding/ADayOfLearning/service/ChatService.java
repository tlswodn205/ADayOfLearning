package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.repository.interfaces.ChatRepository;
import com.tencoding.ADayOfLearning.repository.model.Chat;

@Service
public class ChatService {

	@Autowired
	ChatRepository chatRepository;

	
	// 채팅 내용 조회
	public List<Chat> findByChatRoomId(int chatRoomId) {
		return chatRepository.findByChatRoomId(chatRoomId);
	}

}
