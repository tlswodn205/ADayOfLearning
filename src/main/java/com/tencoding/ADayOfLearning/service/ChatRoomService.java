package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.ADayOfLearning.dto.response.ChatRoomResponseDto;
import com.tencoding.ADayOfLearning.repository.interfaces.ChatRoomRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.ChatRoomUserRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.UserRepository;
import com.tencoding.ADayOfLearning.repository.model.ChatRoom;
import com.tencoding.ADayOfLearning.repository.model.ChatRoomUser;
import com.tencoding.ADayOfLearning.repository.model.User;

@Service
public class ChatRoomService {

	@Autowired
	ChatRoomRepository chatRoomRepository;

	@Autowired
	ChatRoomUserRepository chatRoomUserRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public List<ChatRoomResponseDto> findByUserId(int userId) {
		return chatRoomRepository.findByUserId(userId);
	}

	@Transactional
	public int insert(String user1, String user2) {
		// chat_room insert
		
		ChatRoom chatRoom = new ChatRoom();
		chatRoomRepository.insert(chatRoom);
		// chat_room_user insert
		User userEntity = userRepository.findByUsername(user1);
		ChatRoomUser chatRoomUser = ChatRoomUser.builder()
												.chatRoomId(chatRoom.getChatRoomId())
												.UserId(userEntity.getUserId())
												.build();
		chatRoomUserRepository.insert(chatRoomUser);
		
		userEntity = userRepository.findByUsername(user2);
		chatRoomUser.setUserId(userEntity.getUserId());
		chatRoomUserRepository.insert(chatRoomUser);
		return chatRoom.getChatRoomId();
	}
}
