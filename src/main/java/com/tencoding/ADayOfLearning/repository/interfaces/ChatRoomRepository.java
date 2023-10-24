package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.ADayOfLearning.repository.model.ChatRoom;

@Mapper
public interface ChatRoomRepository {
	public int insert(ChatRoom chatRoom);
	public int updateByChatRoomId(ChatRoom chatRoom);
	public int deleteByChatRoomId(int chatRoomId);
	public ChatRoom findByChatRoomId(int chatRoomId);
	public List<ChatRoom> findByAll();
}