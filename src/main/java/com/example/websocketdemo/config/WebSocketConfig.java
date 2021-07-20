package com.example.websocketdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Configuration
@EnableWebSocketMessageBroker // 웹소켓 서버 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // SockJS는 웹 소켓을 지원하지 않는 브라우저에 대한 대체 옵션을 활성화함
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 대상이 /app 으로 시작하는 메시지가 메시지 처리 메서드로 라우팅되어야 한다고 정의
        registry.setApplicationDestinationPrefixes("/app");

        // 목적지가 /topic으로 시작하는 메시지가 메시지 브로커로 라우팅되어야 한다고 정의
        // 메시지 브로커는 특정 주제를 구독하는 연결된 모든 클라이언트에게 메시지를 브로드캐스트함.
        registry.enableSimpleBroker("/topic");   // Enables a simple in-memory broker


        //   Use this for enabling a Full featured broker like RabbitMQ

        /*
        registry.enableStompBrokerRelay("/topic")
                .setRelayHost("localhost")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest");
                */
    }
}
