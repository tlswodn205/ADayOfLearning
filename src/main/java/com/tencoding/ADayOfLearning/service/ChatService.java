package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.request.ChatMessageRequestDto;
import com.tencoding.ADayOfLearning.repository.interfaces.ChatRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.UserRepository;
import com.tencoding.ADayOfLearning.repository.model.Chat;
import com.tencoding.ADayOfLearning.repository.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	// 채팅 전송 및 저장
	public void insertChat(ChatMessageRequestDto chatMessageRequestDto) {
		log.info("insertChat");
		log.info("{}", chatMessageRequestDto);
		User userEntity = userRepository.findByUsername(chatMessageRequestDto.getSendUsername());
		log.info("{}", userEntity);
		Chat chatEntity = Chat.builder()
				.chatRoomId(chatMessageRequestDto.getChatRoomId())
				.UserId(userEntity.getUserId())
				.context(chatMessageRequestDto.getMessage())
				.viewAt(true)
				.build();
		log.info("{}", chatEntity);
		
		int result = chatRepository.insert(chatEntity);
		log.info("insertChat result : {}", result);
	}

}
