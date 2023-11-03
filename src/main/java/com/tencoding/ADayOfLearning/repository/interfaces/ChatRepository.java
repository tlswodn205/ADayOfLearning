package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.ADayOfLearning.dto.request.ChatMessageRequestDto;
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
	 * 채팅방의 대화내용 조회
	 * @param chatRoomId
	 * @param userId
	 * @return List<ChatMessageResponsoDto>
	 */
	public List<ChatMessageResponsoDto> findByChatRoomIdAndUserId(ChatRoomUser chatRoomUser);
	
	/**
	 * 확인한 대화 업데이트
	 * @param chatRoomUser
	 */
	public void updateViewAt(ChatRoomUser chatRoomUser);
	/**
	 * 전송한 채팅 저장
	 * @param chatMessageRequestDto
	 * @return 
	 */
	public int insertChat(ChatMessageRequestDto chatMessageRequestDto);
	public String findCreatedAtByChatId(int chatId);
}