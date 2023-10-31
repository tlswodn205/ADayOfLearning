package com.tencoding.ADayOfLearning.dto.request;

import lombok.Data;

@Data
public class ChatLeaveRequestDto {
	private int chatRoomId;
	private int userId;
}
