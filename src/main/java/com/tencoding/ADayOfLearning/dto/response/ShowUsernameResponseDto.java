package com.tencoding.ADayOfLearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowUsernameResponseDto {
	private String username;
	private boolean isKakaoLogin;
	
	public ShowUsernameResponseDto(String username) {

		this.username = username;
		
		if(username.indexOf("_kakao")==username.length()-6) {
			this.isKakaoLogin = true;
		}else {
			this.isKakaoLogin = false;
		}
	}
	
}
