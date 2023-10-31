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
public class Chat {
	private int chatId;
	private int chatRoomId;
	private int UserId;
	private String context;
	private boolean viewAt;
	private Timestamp createdAt;
}
