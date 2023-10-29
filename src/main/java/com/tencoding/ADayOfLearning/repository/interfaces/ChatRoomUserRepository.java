package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.ADayOfLearning.repository.model.ChatRoomUser;


@Mapper
public interface ChatRoomUserRepository {
	public int insert(ChatRoomUser chatRoomUser);
	public int updateByChatRoomUserId(ChatRoomUser chatRoomUser);
	public int deleteByChatRoomUserId(int chatRoomUserId);
	public ChatRoomUser findByChatRoomUserId(int chatRoomUserId);
	public List<ChatRoomUser> findByAll();
	public int findByChatRoomIdAndUserId(Integer chatRoomId, int userId);
}