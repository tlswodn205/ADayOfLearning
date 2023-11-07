package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tencoding.ADayOfLearning.dto.request.UpdateUserData;
import com.tencoding.ADayOfLearning.repository.model.Person;


@Mapper
public interface PersonRepository {
	public int insert(Person person);
	public int updateByPersonId(Person person);
	public int deleteByPersonId(int personId);
	public int deleteByUserId(int userId);
	public Person findByPersonId(int personId);
	public List<Person> findByAll();
	public Person findByEmail(String email);
	public Person findByUserId(int userId);
	public int updateByUserId(@Param("updateUserData") UpdateUserData updateUserData,@Param("userId") int userId);
}