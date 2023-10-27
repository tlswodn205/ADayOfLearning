package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.response.ChatRoomResponseDto;
import com.tencoding.ADayOfLearning.repository.interfaces.ChatRoomRepository;

@Service
public class ChatRoomService {

	@Autowired
	ChatRoomRepository chatRoomRepository;

	public List<ChatRoomResponseDto> findByUserId(int userId) {
		return chatRoomRepository.findByUserId(userId);
	}
}
