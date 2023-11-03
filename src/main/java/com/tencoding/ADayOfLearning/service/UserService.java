package com.tencoding.ADayOfLearning.service;

import java.io.File;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tencoding.ADayOfLearning.dto.request.BusinessRequestDto;
import com.tencoding.ADayOfLearning.dto.request.SignInRequestDto;
import com.tencoding.ADayOfLearning.dto.request.SignUpRequestDto;
import com.tencoding.ADayOfLearning.dto.request.UpdateUserData;
import com.tencoding.ADayOfLearning.dto.response.MyPageRequestDto;
import com.tencoding.ADayOfLearning.dto.response.ShowUsernameResponseDto;
import com.tencoding.ADayOfLearning.handler.exception.CustomRestfulException;
import com.tencoding.ADayOfLearning.repository.interfaces.BusinessRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.PersonRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.UserRepository;
import com.tencoding.ADayOfLearning.repository.model.Business;
import com.tencoding.ADayOfLearning.repository.model.Person;
import com.tencoding.ADayOfLearning.repository.model.User;
import com.tencoding.ADayOfLearning.util.Mail;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	BusinessRepository businessRepository;
	
	@Resource(name="mail")
	private Mail mail;

	@Transactional
	public User signIn(SignInRequestDto signInRequestDto) {
		User userEntity = userRepository.findByUsername(signInRequestDto.getUsername());
		String password = signInRequestDto.getPassword();
		
		if(userEntity==null) {
			throw new CustomRestfulException("존재하지 않는 아이디입니다.", HttpStatus.BAD_REQUEST);
		}
		
		
		if(!passwordEncoder.matches(password, userEntity.getPassword())) {
			throw new CustomRestfulException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
		}

		return userEntity;
	}
	
	@Transactional
	public int insertUser(SignUpRequestDto signUpRequestDto) {
		String password = signUpRequestDto.getPassword();
		String hashPwd = passwordEncoder.encode(password);
		User userEntity = signUpRequestDto.toUserEntity(hashPwd);
		userRepository.insert(userEntity);
		int result = personRepository.insert(signUpRequestDto.toPersonEntity(userEntity.getUserId()));
		
		return result;
	}

	@Transactional
	public int usernameDuplicationCheck(String username) {
		User userEntity = userRepository.findByUsername(username);
		if(userEntity==null) {
			return 1;
		}
		return 0;
	}

	@Transactional
	public User findByUserId(int userId) {
		User userEntity = userRepository.findByUserId(userId);
		return userEntity;
	}

	@Transactional
	public User findUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Transactional
	public ShowUsernameResponseDto findUsernameByEmail(String email) {
		String username = userRepository.findUsernameByEmail(email);
		
		if(username==null) {
			throw new CustomRestfulException("존재하지 않는 유저입니다.", HttpStatus.BAD_REQUEST);
		}
		
		
		return new ShowUsernameResponseDto(username);
	}

	@Transactional
	public int updateRandomPasswordByEmailAnd(String email, String username) {
		String insertedUsername = userRepository.findUsernameByEmail(email);
		if(insertedUsername == null) {
			throw new CustomRestfulException("존재하지 않는 유저입니다.", HttpStatus.BAD_REQUEST);
		}
		if(!username.equals(insertedUsername)) {
			throw new CustomRestfulException("아이디가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
		}
		
		String password = RandomStringUtils.randomAlphanumeric(8);
		String hashPwd = passwordEncoder.encode(password);
		
		int result = userRepository.updatePasswordByUsername(username, hashPwd);

		if(result==1) {
			mail.sendPasswordEmail(email, password);
		}
		return result;
		
	}

	@Transactional
	public MyPageRequestDto findUserData(int userId) {
		
		User user = userRepository.findByUserId(userId);
		Person person = personRepository.findByUserId(userId);
		
		MyPageRequestDto myPageRequestDto = new MyPageRequestDto(user, person);
		
		return myPageRequestDto; 
	}

	@Transactional
	public void updateUserData(UpdateUserData updateUserData, int userId) {
		
		if(updateUserData.getPassword() != null) {
			String password = updateUserData.getPassword();
			String hashPwd = passwordEncoder.encode(password);
		
			userRepository.updatePasswordByUserId(userId, hashPwd);
		}
		
		personRepository.updateByUserId(updateUserData, userId);
		
		
	}

	@Transactional
	public void insertBusiness(BusinessRequestDto businessRequestDto, int userId) {
		MultipartFile businessRegistrationImg = businessRequestDto.getBusinessRegistration();
	    try {
	                if (!businessRegistrationImg.isEmpty()) {
	                	
	                	String fileType = businessRegistrationImg.getContentType().substring(6);
	                    String fileName = UUID.randomUUID().toString() +"." + fileType;   //파일이름은 랜덤해야됨. 사용자가올리는 다른 파일이름이 같을 수 있음.
	                    String filePath = "C:\\Users\\GGG\\git\\ADayOfLearning\\src\\main\\resources\\static\\images\\businessRegistrationImg";
	                    FileUtils.copyInputStreamToFile(businessRegistrationImg.getInputStream(), new File(filePath, fileName));
	                    // 여기서 실제 파일이 저장(regist에서 실행됬다), inputStream을 file로 변환하는 메소드
	                    // multipart.transferTo(new File(filePath, fileName)); // 비슷한 역할
	                    String filePathAndName =  "/images/businessRegistrationImg/"+ fileName;
	                    Business businessEntity = businessRequestDto.toEntity(userId, filePathAndName);
	                    businessRepository.insert(businessEntity);
	                    
	                }
	                    
	        }
	    catch (Exception e) {

            System.out.println(e.getMessage());
		}
	        
	}

}