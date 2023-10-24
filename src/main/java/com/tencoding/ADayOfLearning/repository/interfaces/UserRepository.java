package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.ADayOfLearning.repository.model.User;


@Mapper
public interface UserRepository {
	public int insert(User user);
	public int updateByUserId(User user);
	public int deleteByUserId(int id);
	public User findByUserId(int id);
	public List<User> findByAll();
}