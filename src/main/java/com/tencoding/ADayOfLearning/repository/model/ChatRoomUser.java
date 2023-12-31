package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomUser {
	private int chatRoomUserId;
	private int chatRoomId;
	private int userId;
	private Timestamp startAt;
	private Timestamp createdAt;
}
