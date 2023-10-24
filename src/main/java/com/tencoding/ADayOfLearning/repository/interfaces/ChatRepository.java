package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.ADayOfLearning.repository.model.Chat;

@Mapper
public interface ChatRepository {
	public int insert(Chat chat);
	public int updateByChatId(Chat chat);
	public int deleteByChatId(int chatId);
	public Chat findByChatId(int chatId);
	public List<Chat> findByAll();
}