package com.exemple.ai.assistant;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatAccountClient {

    private final ChatClient chatClient;

    public ChatAccountClient(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String generate() {
        return chatClient
                .prompt("Quelle est la capitale de la France?")
                .call()
                .content();
    }
}
