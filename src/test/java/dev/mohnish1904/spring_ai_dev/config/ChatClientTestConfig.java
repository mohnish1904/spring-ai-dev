package dev.mohnish1904.spring_ai_dev.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ChatClientTestConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder){
        return builder.build();
    }
}
