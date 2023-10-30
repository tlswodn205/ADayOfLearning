package com.tencoding.ADayOfLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.ADayOfLearning.dto.request.ChatMessageRequestDto;
import com.tencoding.ADayOfLearning.repository.interfaces.ChatRoomUserRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.UserRepository;
import com.tencoding.ADayOfLearning.repository.model.ChatRoomUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatRoomUserService {

	@Autowired
	ChatRoomUserRepository chatRoomUserRepository;

	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public void insert(int chatRoomId, int userId) {
		ChatRoomUser chatRoomUser = ChatRoomUser.builder()
												.chatRoomId(chatRoomId)
												.UserId(userId)
												.build();
		
		log.info("chatRoomUser : {}", chatRoomUser);
		chatRoomUserRepository.insert(chatRoomUser);
	}

	@Transactional
	public void chatRoomUserCheck(ChatMessageRequestDto chatMessageRequestDto) {
		log.info("chatRoomUserCheck : {}", chatMessageRequestDto);
		int chatRoomId = chatMessageRequestDto.getChatRoomId();
		// 테이블에 유저가 있는지 확인
		// 한쪽만 나간거 대비
		int result = chatRoomUserRepository.findByChatRoomIdAndUserId(chatRoomId, chatMessageRequestDto.getSendUserId());
		log.info("result1 : {}", result);
		if(result != 1) {
			// 데이터가 없으면 생성
			insert(chatRoomId, chatMessageRequestDto.getSendUserId());
		}
		log.info("result2 : {}", result);
		result = chatRoomUserRepository.findByChatRoomIdAndUserId(chatRoomId, chatMessageRequestDto.getReceiveUserId());
		if(result != 1) {
			// 데이터가 없으면 생성
			insert(chatRoomId, chatMessageRequestDto.getReceiveUserId());
		}
	}

}
