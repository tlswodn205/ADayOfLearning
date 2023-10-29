package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.ADayOfLearning.dto.response.ChatRoomResponseDto;
import com.tencoding.ADayOfLearning.repository.interfaces.ChatRoomRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.UserRepository;
import com.tencoding.ADayOfLearning.repository.model.ChatRoom;

@Service
public class ChatRoomService {

	@Autowired
	ChatRoomRepository chatRoomRepository;

	@Autowired
	ChatRoomUserService chatRoomUserService;
	
	@Autowired
	UserRepository userRepository;
	
	public List<ChatRoomResponseDto> findByUserId(int userId) {
		return chatRoomRepository.findByUserId(userId);
	}

	@Transactional
	public int insert(int sendUserId, int receiveUserId) {
		// chat_room insert
		ChatRoom chatRoom = new ChatRoom();
		chatRoomRepository.insert(chatRoom);
		
		// chat_room_user insert
		chatRoomUserService.insert(chatRoom.getChatRoomId(), sendUserId);
		chatRoomUserService.insert(chatRoom.getChatRoomId(), receiveUserId);
		
		return chatRoom.getChatRoomId();
	}
	
}
