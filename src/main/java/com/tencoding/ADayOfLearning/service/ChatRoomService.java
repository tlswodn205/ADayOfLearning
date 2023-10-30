package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.ADayOfLearning.dto.response.ChatRoomResponseDto;
import com.tencoding.ADayOfLearning.repository.interfaces.ChatRoomRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.UserRepository;
import com.tencoding.ADayOfLearning.repository.model.ChatRoom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatRoomService {

	@Autowired
	ChatRoomRepository chatRoomRepository;
	@Autowired
	ChatRoomUserService chatRoomUserService;
	@Autowired
	UserRepository userRepository;
	
	/**
	 * 참여중인 채팅방 목록 조회 service
	 * @param userId
	 * @return List<ChatRoomResponseDto>
	 */
	public List<ChatRoomResponseDto> findByUserId(int userId) {
		return chatRoomRepository.findByUserId(userId);
	}

	/**
	 * 2개의 userId (param 참고)가 함께 있는 chatRoomId조회
	 * 없을경우 chatRoom 추가 및 chatRoomUser 추가 
	 * @param sendUserId
	 * @param receiveUserId
	 * @return chatRoomId
	 */
	@Transactional
	public int insert(int sendUserId, int receiveUserId) {
		log.info("chatInsert");
		log.info("userId : {} - {}", sendUserId, receiveUserId);
		int chatRoomId = chatRoomRepository.findCheckChatRoomByUserId(sendUserId, receiveUserId);
		// chat_room insert
		if(chatRoomId == 0) {
			log.info("chatRoom 생성");
			// 채팅룸 생성
			ChatRoom chatRoom = new ChatRoom();
			chatRoomRepository.insert(chatRoom);
			chatRoomId = chatRoom.getChatRoomId();
			
			// chat_room_user 생성
			chatRoomUserService.insert(chatRoomId, sendUserId);
			chatRoomUserService.insert(chatRoomId, receiveUserId);
		}
		
		log.info("최종 chatRoomId : {}", chatRoomId);
		
		return chatRoomId;
	}
}
