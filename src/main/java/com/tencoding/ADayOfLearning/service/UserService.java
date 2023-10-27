package com.tencoding.ADayOfLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.request.SignInRequestDto;
import com.tencoding.ADayOfLearning.dto.request.SignUpRequestDto;
import com.tencoding.ADayOfLearning.handler.exception.CustomRestfulException;
import com.tencoding.ADayOfLearning.repository.interfaces.PersonRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.UserRepository;
import com.tencoding.ADayOfLearning.repository.model.User;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public User signIn(SignInRequestDto signInRequestDto) {
		User userEntity = userRepository.findByUsername(signInRequestDto.getUsername());

		String hashPwd = passwordEncoder.encode(signInRequestDto.getUsername());
		if(userEntity==null) {
			throw new CustomRestfulException("존재하지 않는 아이디입니다.", HttpStatus.BAD_REQUEST);
		}
		
		
		if(userEntity.getPassword().equals(hashPwd)) {
			throw new CustomRestfulException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
		}
		
		
		return userEntity;
	}

	public int insertUser(SignUpRequestDto signUpRequestDto) {

		String hashPwd = passwordEncoder.encode(signUpRequestDto.getUsername());
		int userId = userRepository.insert(signUpRequestDto.toUserEntity(hashPwd));
		
		int result = personRepository.insert(signUpRequestDto.toPersonEntity(userId));
		
		
		return result;
	}

	public int usernameDuplicationCheck(String username) {
		User userEntity = userRepository.findByUsername(username);
		if(userEntity==null) {
			return 1;
		}
		return 0;
	}
	
	
	
	

	
}