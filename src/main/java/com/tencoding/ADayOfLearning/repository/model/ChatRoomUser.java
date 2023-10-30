package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ChatRoomUser {
	private int chatRoomUserId;
	private int chatRoomId;
	private int UserId;
	private int startAt;
	private Timestamp createdAt;
}
