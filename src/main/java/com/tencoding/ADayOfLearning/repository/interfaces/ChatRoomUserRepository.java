package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tencoding.ADayOfLearning.dto.request.NewChatRequestDto;
import com.tencoding.ADayOfLearning.repository.model.ChatRoomUser;


@Mapper
public interface ChatRoomUserRepository {
	public int insert(ChatRoomUser chatRoomUser);
	public int updateByChatRoomUserId(ChatRoomUser chatRoomUser);
	public int deleteByChatRoomUserId(int chatRoomUserId);
	public ChatRoomUser findByChatRoomUserId(int chatRoomUserId);
	public List<ChatRoomUser> findByAll();
	/**
	 * 채팅방 최초 입장 - ChatRoomUser 데이터 추가
	 * @param ChatRoomEnterRequestDto
	 */
	public int insertEnter(NewChatRequestDto ChatRoomEnterRequestDto);
	/**
	 *  채팅방 입장 상태 확인 (start_at)
	 * @param chatRoomId
	 * @param userId
	 */
	public ChatRoomUser findByChatRoomIdAndUserId(@Param(value="chatRoomId") int chatRoomId,@Param(value="userId") int userId);
	
	/**
	 * startAt을 현재 시간으로 업데이트
	 * @param chatRoomUser
	 */
	public void updateStartAtByChatRoomUser(ChatRoomUser chatRoomUser);
	
	/**
	 * 채팅방 나가기
	 * @param chatRoomUser
	 */
	public void deleteStartAtByChatRoomIdAndUserId(ChatRoomUser chatRoomUser);
}