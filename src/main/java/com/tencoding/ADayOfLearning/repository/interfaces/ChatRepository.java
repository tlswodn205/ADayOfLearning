package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tencoding.ADayOfLearning.dto.request.ChatMessageRequestDto;
import com.tencoding.ADayOfLearning.repository.model.Chat;

@Mapper
public interface ChatRepository {
	public int insert(Chat chat);
	public int updateByChatId(Chat chat);
	public int deleteByChatId(int chatId);
	public Chat findByChatId(int chatId);
	public List<Chat> findByAll();
	
	/**
	 * 채팅 대화 목록
	 * @param chatRoomId
	 * @param userId
	 * @return List<ChatMessageRequestDto>
	 */
	public List<ChatMessageRequestDto> findByChatRoomIdAndUserId(@Param(value = "chatRoomId") int chatRoomId, @Param(value = "userId") int userId);
}