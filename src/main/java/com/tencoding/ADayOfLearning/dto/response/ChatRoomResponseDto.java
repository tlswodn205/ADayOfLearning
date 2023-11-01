package com.tencoding.ADayOfLearning.dto.response;

import lombok.Data;

@Data
public class ChatRoomResponseDto {
	// 참여중인 체팅방 목록 조회 응답 dto
	private int chatRoomId;
	private int userId;
	private String username;
	private String name;
	private int viewCount;
}
