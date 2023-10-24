package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ChatRoom {
	private int chatRoomId;
	private Timestamp createdAt;
}
