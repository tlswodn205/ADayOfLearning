package com.tencoding.ADayOfLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.request.SignUpRequestDto;
import com.tencoding.ADayOfLearning.repository.interfaces.PersonRepository;
import com.tencoding.ADayOfLearning.repository.model.Person;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;

	public void insertPerson(SignUpRequestDto signUpRequestDto, int userId) {
		Person personEntity = signUpRequestDto.toPersonEntity(userId);
		
		personRepository.insert(personEntity);
	}
}
