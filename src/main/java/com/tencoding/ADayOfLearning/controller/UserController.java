package com.tencoding.ADayOfLearning.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.tencoding.ADayOfLearning.dto.request.BusinessRequestDto;
import com.tencoding.ADayOfLearning.dto.request.KakaoProfile;
import com.tencoding.ADayOfLearning.dto.request.OAuthToken;
import com.tencoding.ADayOfLearning.dto.request.SignInRequestDto;
import com.tencoding.ADayOfLearning.dto.request.SignUpRequestDto;
import com.tencoding.ADayOfLearning.dto.request.UpdateUserData;
import com.tencoding.ADayOfLearning.dto.response.MyPageRequestDto;
import com.tencoding.ADayOfLearning.dto.response.ShowUsernameResponseDto;
import com.tencoding.ADayOfLearning.handler.exception.CustomRestfulException;
import com.tencoding.ADayOfLearning.repository.model.Person;
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
		System.out.println(principal.getPassword() +" ??? " + signInRequestDto.getPassword());
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
	

	@GetMapping("/kakao/callback")
	public String kakaoCallback(@RequestParam String code, Model model) {
		System.out.println("메서드 동작");
		
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "ccdeb94ef92aa4067769d9ed3712c815");
		params.add("redirect_uri", "http://localhost:8080/user/kakao/callback");
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> reqMes = 
				new HttpEntity<>(params, headers);
		
		ResponseEntity<OAuthToken> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, reqMes, OAuthToken.class);
		System.out.println("액세스 토큰 확인" + response.getBody().toString());
		
		RestTemplate ret2 = new RestTemplate();
		
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + response.getBody().getAccessToken());
		headers2.add("Content-type", "Content-type: application/x-www-form-urlencoded;charset=utf-8");
		HttpEntity<MultiValueMap<String, String>> kakaoInfo = new HttpEntity<>(headers2);
		
		ResponseEntity<KakaoProfile> response2 = ret2.exchange
				("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, kakaoInfo, KakaoProfile.class);
		System.out.println("------------------------------------------------");
		System.out.println(response2.getBody().getKakaoAccount().getEmail());
		
		System.out.println("-------카카오 서버에 정보 받기 완료-------");
		
		KakaoProfile kakaoProfile = response2.getBody();
		
		SignInRequestDto signInRequestDto = SignInRequestDto
				.builder()
				.username(kakaoProfile.getKakaoAccount().getEmail()+"_"+kakaoProfile.getId()+"_kakao")
				.password("tencoKey")
				.build();

		User oldUser = userService.findUsername(signInRequestDto.getUsername());
		if (oldUser == null) {
			model.addAttribute("signInRequestDto", signInRequestDto);
			return "/user/signUp";
		}
		oldUser.setPassword(null);
		session.setAttribute(Define.PRINCIPAL, oldUser);
		
		//session.setAttribute(Define.PRINCIPAL, oldUser);
		return "redirect:/";
		
	}
	
	@GetMapping("/emailCheck")
	@ResponseBody
	public int emailCheck(@RequestParam String email) {
		String emailCheck = personService.emailDuplicationCheck(email);
		session.setAttribute(emailCheck, email);
		session.setMaxInactiveInterval(180);
		return 1;
	}
	
	@GetMapping("/certificationNumber")
	@ResponseBody
	public int certificationNumber(@RequestParam String email,@RequestParam String certificationNumber)  {
		certificationNumber = "emailCheck_"+certificationNumber; 
		String emailStore = (String)session.getAttribute(certificationNumber);
		
		if(emailStore == null) {
			return 0;
		}
		
		if(!emailStore.equals(email)) {
			throw new CustomRestfulException("이메일이 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
		}
		
		return 1;
	}
	
	@GetMapping("/findUsername")
	public String findUsername()  {
		return "user/findUsername";
	}
	
	@PostMapping("/showUsername")
	public String showUsername(String email, Model model)  {
		ShowUsernameResponseDto showUsernameResponseDto = userService.findUsernameByEmail(email);
		model.addAttribute("showUsernameResponseDto", showUsernameResponseDto);
		
		return "user/showUsername";
	}
	
	@GetMapping("/findPassword")
	public String findPassword()  {
		return "user/findPassword";
	}
	
	@GetMapping("/showPassword")
	@ResponseBody
	public int showPassword(@RequestParam String email, @RequestParam String username)  {
		int result = userService.updateRandomPasswordByEmailAnd(email, username);
		return result;
	}
	
	@GetMapping("/userMyPage")
	public String getUserMyPage(Model model) {
		
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		
		MyPageRequestDto myPageRequestDto = userService.findUserData(user.getUserId());
		
		model.addAttribute("myPageRequestDto", myPageRequestDto);
		
		return "/user/userMyPage";
	}
	
	@PostMapping("/updateUserData")
	public String updateUserData(UpdateUserData updateUserData) {
		
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		
		userService.updateUserData(updateUserData, user.getUserId());
		
		return "redirect:/user/userMyPage";
	}
	
	@GetMapping("/businessRequest")
	public String getBusinessRequest(Model model) {
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		Person person = personService.findPersonByUserId(user.getUserId());
		
		model.addAttribute("person", person);
		
		return "/user/businessRequest";
	}
	
	@PostMapping("/businessRequest")
	public String postBusinessRequest(BusinessRequestDto businessRequestDto) {

		User user = (User) session.getAttribute(Define.PRINCIPAL);
		
		userService.insertBusiness(businessRequestDto, user.getUserId());
		
		return "/";
	}
	
}
