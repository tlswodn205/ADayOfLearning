package com.tencoding.ADayOfLearning.dto.response;

import lombok.Data;

@Data
public class ChatMessageResponsoDto {
	private int chatRoomId; // 방 번호
	private int userId;
	private String name;
	private String message; // 메시지
	private String createdAt;
}
