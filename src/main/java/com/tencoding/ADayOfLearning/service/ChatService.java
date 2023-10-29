package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.request.ChatMessageRequestDto;
import com.tencoding.ADayOfLearning.repository.interfaces.ChatRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.UserRepository;
import com.tencoding.ADayOfLearning.repository.model.Chat;
import com.tencoding.ADayOfLearning.repository.model.User;

@Service
public class ChatService {

	@Autowired
	ChatRepository chatRepository;

	@Autowired
	UserRepository userRepository;
	
	// 채팅 내용 조회
	public List<ChatMessageRequestDto> findByChatRoomId(int chatRoomId) {
		return chatRepository.findByChatRoomId(chatRoomId);
	}

	public int insertChat(ChatMessageRequestDto chatMessageRequestDto) {
		User userEntity = userRepository.findByUsername(chatMessageRequestDto.getSendUsername());
		Chat chatEntity = Chat.builder()
				.chatRoomId(chatMessageRequestDto.getChatRoomId())
				.UserId(userEntity.getUserId())
				.context(chatMessageRequestDto.getMessage())
				.viewAt(true)
				.build();
		return chatRepository.insert(chatEntity);
	}

}
