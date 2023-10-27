package com.tencoding.ADayOfLearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageRequestDto {
    // 메시지  타입 : 입장, 채팅
	
    private Long chatRoomId; // 방 번호
    private String name; // 채팅을 보낸 사람
    private String message; // 메시지
}