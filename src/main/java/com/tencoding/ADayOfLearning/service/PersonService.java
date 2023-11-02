package com.tencoding.ADayOfLearning.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.handler.exception.CustomRestfulException;
import com.tencoding.ADayOfLearning.repository.interfaces.PersonRepository;
import com.tencoding.ADayOfLearning.repository.model.Person;
import com.tencoding.ADayOfLearning.util.Mail;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;

	@Resource(name="mail")
	private Mail mail;
	
	
	public String emailDuplicationCheck(String email) {
		Person personEntity = personRepository.findByEmail(email);
		
		if(personEntity!=null) {
			throw new CustomRestfulException("이미 존재하는 이메일 입니다.", HttpStatus.BAD_REQUEST);
		}
		
		String randomStr = RandomStringUtils.randomAlphanumeric(6);
		mail.sendAuthEmail(email, randomStr);
		
		return "emailCheck_"+randomStr;
	}


	public Person findPersonByUserId(int userId) {
		Person person = personRepository.findByUserId(userId);
		return person;
	}
}
