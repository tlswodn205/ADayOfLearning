package com.tencoding.ADayOfLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	 * chatUserRoom 추가
	 * @param chatRoomId
	 * @param userId
	 */
	@Transactional
	public void insert(int chatRoomId, int userId) {
		ChatRoomUser chatRoomUser = ChatRoomUser.builder()
												.chatRoomId(chatRoomId)
												.UserId(userId)
												.build();
		
		log.info("chatRoomUser : {}", chatRoomUser);
		chatRoomUserRepository.insert(chatRoomUser);
	}


}
