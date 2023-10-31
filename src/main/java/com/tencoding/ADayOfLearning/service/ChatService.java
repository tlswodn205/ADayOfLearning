package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.request.ChatMessageRequestDto;
import com.tencoding.ADayOfLearning.repository.interfaces.ChatRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.ChatRoomUserRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.UserRepository;
import com.tencoding.ADayOfLearning.repository.model.Chat;
import com.tencoding.ADayOfLearning.repository.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatService {

	@Autowired
	ChatRoomUserService chatRoomUserService;
	
	@Autowired
	ChatRepository chatRepository;
	@Autowired
	ChatRoomUserRepository chatRoomUserRepository;

	@Autowired
	UserRepository userRepository;
	
	/** 
	 * 채팅 대화 내용 리스트 조회
	 * @param chatRoomId
	 * @param userId
	 * @return List<ChatMessageRequestDto>
	 */
	public List<ChatMessageRequestDto> findByChatRoomId(int chatRoomId, int userId) {
		return chatRepository.findByChatRoomIdAndUserId(chatRoomId, userId);
	}

	/**
	 * 채팅 대화 내용 저장
	 * @param chatMessageRequestDto
	 */
	public void insertChat(ChatMessageRequestDto chatMessageRequestDto) {
		log.info("insertChat");
		log.info("{}", chatMessageRequestDto);
		chatRoomUserService.updateStartAt(chatMessageRequestDto.getChatRoomId(), chatMessageRequestDto.getReceiveUserId());
		User userEntity = userRepository.findByUsername(chatMessageRequestDto.getSendUsername());
		Chat chat = Chat.builder()
				.chatRoomId(chatMessageRequestDto.getChatRoomId())
				.userId(userEntity.getUserId())
				.context(chatMessageRequestDto.getMessage())
				.viewAt(true)
				.build();
		chatRepository.insert(chat);
	}


}
