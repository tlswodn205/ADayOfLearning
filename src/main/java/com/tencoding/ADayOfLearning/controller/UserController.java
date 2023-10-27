package com.tencoding.ADayOfLearning.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tencoding.ADayOfLearning.dto.request.SignInRequestDto;
import com.tencoding.ADayOfLearning.dto.request.SignUpRequestDto;
import com.tencoding.ADayOfLearning.handler.exception.CustomRestfulException;
import com.tencoding.ADayOfLearning.repository.model.User;
import com.tencoding.ADayOfLearning.service.PersonService;
import com.tencoding.ADayOfLearning.service.UserService;
import com.tencoding.ADayOfLearning.util.Define;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	PersonService personService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/test")
	public String test1() {
		return "example";
	}
	
	@GetMapping("/signIn")
	public String getSignIn() {
		return "/user/signIn";
	}
	
	@PostMapping("/signIn")
	public String postSignIn(SignInRequestDto signInRequestDto) {
		
		if(signInRequestDto.getUsername() == null || signInRequestDto.getUsername().isEmpty()) {
			throw new CustomRestfulException("아이디를 입력하세요", HttpStatus.BAD_REQUEST);
		}
		
		if(signInRequestDto.getPassword() == null || signInRequestDto.getPassword().isEmpty()) {
			throw new CustomRestfulException("패스워드를 입력하세요", HttpStatus.BAD_REQUEST);
		}
		
		User principal = userService.signIn(signInRequestDto);
		
		session.setAttribute(Define.PRINCIPAL, principal);
		
		return "/user/signIn";
	}
	
	@GetMapping("/signUp")
	public String getSignUp() {
		return "/user/signUp";
	}
	
	@PostMapping("/signUp")
	public String postSignUp(SignUpRequestDto signUpRequestDto) {
		if(signUpRequestDto.getUsername() == null || signUpRequestDto.getUsername().isEmpty()) {
			throw new CustomRestfulException("아이디를 입력하세요.", HttpStatus.BAD_REQUEST);
		}
		
		if(signUpRequestDto.getPassword() == null || signUpRequestDto.getPassword().isEmpty()) {
			throw new CustomRestfulException("비밀번호를 입력하세요.", HttpStatus.BAD_REQUEST);
		}
		System.out.println(signUpRequestDto.getPassword());
		System.out.println(signUpRequestDto.getPasswordCheck());
		
		if(!signUpRequestDto.getPassword().equals(signUpRequestDto.getPasswordCheck())) {
			throw new CustomRestfulException("비밀번호와 비밀번호 확인이 다릅니다.", HttpStatus.BAD_REQUEST);
		}
		
		if(signUpRequestDto.getName() == null || signUpRequestDto.getName().isEmpty()) {
			throw new CustomRestfulException("이름을 입력하세요.", HttpStatus.BAD_REQUEST);
		}
		
		if(signUpRequestDto.getEmail() == null || signUpRequestDto.getEmail().isEmpty()) {
			throw new CustomRestfulException("이메일을 입력하세요.", HttpStatus.BAD_REQUEST);
		}
		
		if(signUpRequestDto.getAddress() == null || signUpRequestDto.getAddress().isEmpty()) {
			throw new CustomRestfulException("주소를 입력하세요.", HttpStatus.BAD_REQUEST);
		}
		
		if(signUpRequestDto.getPhoneNumber() == null || signUpRequestDto.getPhoneNumber().isEmpty()) {
			throw new CustomRestfulException("전화번호를 입력하세요.", HttpStatus.BAD_REQUEST);
		}
		
		if(signUpRequestDto.getBirthday() == null) {
			throw new CustomRestfulException("생일을 입력하세요.", HttpStatus.BAD_REQUEST);
		}
		
		int userId = userService.insertUser(signUpRequestDto);	
		
		return "redirect:/user/signIn";
	}
	
	
	@GetMapping("/usernameDuplicationCheck")
	@ResponseBody
	public int usernameDuplicationCheck(@Param(value = "username") String username) {
		
		if(username == null) {
			throw new CustomRestfulException("아이디를 입력하세요", HttpStatus.BAD_REQUEST);
		}
		
		int result = userService.usernameDuplicationCheck(username);
		
		
		return result;
	}
	
}
