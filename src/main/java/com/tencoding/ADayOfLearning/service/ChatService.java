package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.request.ChatMessageRequestDto;
import com.tencoding.ADayOfLearning.dto.request.ChatRoomEnterRequestDto;
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
	
	/**
	 * 채팅 대화 리스트 조회
	 * @param chatRoomId
	 * @return
	 */
	public List<ChatMessageRequestDto> findByChatRoomId(int chatRoomId) {
		return chatRepository.findByChatRoomId(chatRoomId);
	}

	/**
	 * 채팅 내용 저장
	 * @param chatMessageRequestDto
	 */
	public void insertChat(ChatMessageRequestDto chatMessageRequestDto) {
		log.info("insertChat");
		log.info("{}", chatMessageRequestDto);
		User userEntity = userRepository.findByUsername(chatMessageRequestDto.getSendUsername());
		Chat chat = Chat.builder()
				.chatRoomId(chatMessageRequestDto.getChatRoomId())
				.UserId(userEntity.getUserId())
				.context(chatMessageRequestDto.getMessage())
				.viewAt(true)
				.build();
		
		int result = chatRepository.insert(chat);
		insertEnter(chatMessageRequestDto.getChatRoomId(), chatMessageRequestDto.getReceiveUserId());
		log.info("insertChat result : {}", result);
	}

	/**
	 * 최초 입장인지 확인 - context null인 내용 입력
	 * @param chatRoomEnterRequestDto
	 */
	public void insertEnter(int chatRoomId, int userId) {
		Chat chatEntity = chatRepository.findByChatRoomIdAndUserId(chatRoomId, userId);
		log.info("chatService insertEnter - {}", chatEntity);
		if(chatEntity == null) {
			chatEntity = Chat.builder()
							.chatRoomId(chatRoomId)
							.UserId(userId)
							.build();
			chatRepository.insert(chatEntity);
		}
	}

}
