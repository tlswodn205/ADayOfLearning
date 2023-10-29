package com.tencoding.ADayOfLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.repository.interfaces.ChatRoomUserRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.UserRepository;
import com.tencoding.ADayOfLearning.repository.model.ChatRoomUser;

@Service
public class ChatRoomUserService {

	@Autowired
	ChatRoomUserRepository chatRoomUserRepository;

	@Autowired
	UserRepository userRepository;
	
	public void insert(int chatRoomId, int userId) {
		ChatRoomUser chatRoomUser = ChatRoomUser.builder()
												.chatRoomId(chatRoomId)
												.UserId(userId)
												.build();
		chatRoomUserRepository.insert(chatRoomUser);
	}

	public void chatRoomUserCheck(Integer chatRoomId, int sendUserId, int receiveUserId) {
		// 테이블에 유저가 있는지 확인
		int result = chatRoomUserRepository.findByChatRoomIdAndUserId(chatRoomId, sendUserId);
		if(result != 1) {
			// 데이터가 없으면 생성
			insert(chatRoomId, sendUserId);
		}
		result = chatRoomUserRepository.findByChatRoomIdAndUserId(chatRoomId, receiveUserId);
		if(result != 1) {
			// 데이터가 없으면 생성
			insert(chatRoomId, receiveUserId);
		}
	}

}
