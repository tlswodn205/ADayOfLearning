package com.tencoding.ADayOfLearning.dto.response;

import lombok.Data;

@Data
public class ChatMessageResponsoDto {
	private int chatRoomId; // 방 번호
	private int userId;
	private String username;
	private String message; // 메시지
}
