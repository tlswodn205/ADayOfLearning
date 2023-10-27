package com.tencoding.ADayOfLearning.dto.request;

import lombok.Data;

@Data
public class ChatRoomEnterRequestDto {
	private int chatRoomId;
	private String username;
	private String chatUsername;
}
