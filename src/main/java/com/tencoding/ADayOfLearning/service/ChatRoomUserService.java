package com.tencoding.ADayOfLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.ADayOfLearning.dto.request.ChatRoomEnterRequestDto;
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
	
	/**
	 * 채팅방 생성 후 chatRoomUser 추가
	 * @param chatRoomId
	 * @param userId
	 * @param timestamp 
	 */
	@Transactional
	public void insertEnter(int chatRoomId, int userId, String startAt) {
		ChatRoomEnterRequestDto chatRoomUser = ChatRoomEnterRequestDto.builder()
												.chatRoomId(chatRoomId)
												.userId(userId)
												.startAt(startAt)
												.build();
		log.info("chatRoomUser : {}", chatRoomUser);
		chatRoomUserRepository.insertEnter(chatRoomUser);
	}

	/**
	 * 재입장 확인 - chatRoomUser start_at 확인
	 * @param chatRoomId
	 * @param userId
	 */
	public void updateStartAt(int chatRoomId, int userId) {
		ChatRoomUser chatRoomUser = chatRoomUserRepository.findByChatRoomIdAndUserId(chatRoomId, userId);
		log.info("{}", chatRoomUser);
		if(chatRoomUser.getStartAt() == null) {
			chatRoomUserRepository.updateStartAtByChatRoomUser(chatRoomUser);
		}
	}
	
	/**
	 * 채팅방 나가기
	 * @param chatRoomId
	 * @param userId
	 */
	@Transactional
	public void delete(int chatRoomId, int userId) {
		ChatRoomUser chatRoomUser = ChatRoomUser.builder()
												.chatRoomId(chatRoomId)
												.userId(userId)
												.build();
		chatRoomUserRepository.deleteStartAtByChatRoomIdAndUserId(chatRoomUser);
	}

}
