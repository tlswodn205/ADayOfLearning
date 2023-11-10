package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.ADayOfLearning.dto.request.ChatMessageRequestDto;
import com.tencoding.ADayOfLearning.dto.response.ChatMessageResponsoDto;
import com.tencoding.ADayOfLearning.repository.interfaces.ChatRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.ChatRoomUserRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.UserRepository;
import com.tencoding.ADayOfLearning.repository.model.Chat;
import com.tencoding.ADayOfLearning.repository.model.ChatRoomUser;
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
	 * @return List<ChatMessageResponsoDto>
	 */
	@Transactional
	public List<ChatMessageResponsoDto> chatRoomEnter(int chatRoomId, int userId) {
		// 히스토리 조회
		ChatRoomUser chatRoomUser = chatRoomUserRepository.findByChatRoomIdAndUserId(chatRoomId, userId);
		
		List<ChatMessageResponsoDto> chatList = chatRepository.findByChatRoomIdAndUserId(chatRoomUser);
		// view_at 0으로 update
		if(chatList != null) {
			updateViewAt(chatRoomUser);
		}
		return chatList;
	}

	/**
	 * 채팅 내용 확인 update
	 * @param chatRoomUser
	 */
	@Transactional
	private void updateViewAt(ChatRoomUser chatRoomUser) {
		chatRepository.updateViewAt(chatRoomUser);
	}

	/**
	 * 채팅 대화 내용 저장
	 * @param chatMessageRequestDto
	 */
	@Transactional
	public String insertChat(ChatMessageRequestDto chatMessageRequestDto) {
		log.info("insertChat");
		log.info("{}", chatMessageRequestDto);
		chatRoomUserService.updateStartAt(chatMessageRequestDto.getChatRoomId(), chatMessageRequestDto.getReceiveUserId());
//		User userEntity = userRepository.findByUserId(chatMessageRequestDto.getSendUserId());
		
		Chat chatEntity = Chat.builder()
				.chatRoomId(chatMessageRequestDto.getChatRoomId())
				.userId(chatMessageRequestDto.getSendUserId())
				.context(chatMessageRequestDto.getMessage())
				.viewAt(true)
				.build();
		chatRepository.insert(chatEntity);
		String createdAt = chatRepository.findCreatedAtByChatId(chatEntity.getChatId());
		ChatRoomUser chatRoomUser = ChatRoomUser.builder()
												.chatRoomId(chatMessageRequestDto.getChatRoomId())
												.userId(chatMessageRequestDto.getSendUserId())
												.build();
		updateViewAt(chatRoomUser);
		return createdAt;
	}


}
