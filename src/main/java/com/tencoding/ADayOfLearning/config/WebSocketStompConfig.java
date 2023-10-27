package com.tencoding.ADayOfLearning.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/chat")
        	.setAllowedOrigins("http://localhost:8080")
        	.withSockJS()
            .setClientLibraryUrl("http://localhost:8080/myapp/js/sock-client.js");
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// Client에서 send 요청 처리
        registry.setApplicationDestinationPrefixes("/pub");
        // 해당 경로로 SimpleBroker 등록 -> 해당 경로를 SUBSCRIBE 하는 Client에게 메시지 전달
        registry.enableSimpleBroker("/sub");
	}

}
