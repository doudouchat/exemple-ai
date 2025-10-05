package com.exemple.ai.assistant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.exemple.ai.assistant.core.AssistantConfiguration;

@SpringBootTest(classes = AssistantConfiguration.class)
@ActiveProfiles("test")
class ChatAccountClientTest {

    @Autowired
    private ChatAccountClient chatAccountClient;

    @Test
    void generateOneResponse() {

        // When perform
        var response = chatAccountClient.generate();

        // Then check response
        assertThat(response).contains("Paris");

    }
}
