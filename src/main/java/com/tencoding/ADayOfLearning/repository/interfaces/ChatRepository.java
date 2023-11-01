package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.ADayOfLearning.dto.response.ChatMessageResponsoDto;
import com.tencoding.ADayOfLearning.repository.model.Chat;
import com.tencoding.ADayOfLearning.repository.model.ChatRoomUser;

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
	 * @return List<ChatMessageResponsoDto>
	 */
	public List<ChatMessageResponsoDto> findByChatRoomIdAndUserId(ChatRoomUser chatRoomUser);
	
	public void updateViewAt(ChatRoomUser chatRoomUser);
}