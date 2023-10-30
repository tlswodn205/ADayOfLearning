package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tencoding.ADayOfLearning.dto.response.ChatRoomResponseDto;
import com.tencoding.ADayOfLearning.repository.model.ChatRoom;

@Mapper
public interface ChatRoomRepository {
	public int insert(ChatRoom chatRoom);
	public int updateByChatRoomId(ChatRoom chatRoom);
	public int deleteByChatRoomId(int chatRoomId);
	public ChatRoom findByChatRoomId(int chatRoomId);
	public List<ChatRoom> findByAll();
	
	/**
	 * 참여중인 채팅방 리스트
	 * @param userId
	 * @return List<ChatRoomResponseDto>
	 */
	public List<ChatRoomResponseDto> findByUserId(int userId);
	
	/**
	 * user1와 user2의 채팅방 여부 확인
	 * 있으면 chatRoomId 값, 없으면 0
	 * @param userId1
	 * @param userId2
	 * @return int 
	 */
	public int findCheckChatRoomByUserId(@Param(value = "userId1") int userId1,@Param(value = "userId2") int userId2);
}