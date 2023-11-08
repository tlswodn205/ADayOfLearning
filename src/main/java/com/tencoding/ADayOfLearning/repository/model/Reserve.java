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
public class Reserve {
	private int reserveId;
	private int userId;
	private int lectureSessionId;
	private Timestamp createdAt;
}
