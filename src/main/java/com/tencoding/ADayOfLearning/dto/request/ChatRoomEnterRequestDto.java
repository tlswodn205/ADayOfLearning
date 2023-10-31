package com.tencoding.ADayOfLearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomEnterRequestDto {
	// 채팅 유저정보와 함께 체팅 페이지 이동할때 사용
	private int chatRoomId;
	private int userId; // 채팅상대 userId
	private String username; // 채팅상대 username
}
