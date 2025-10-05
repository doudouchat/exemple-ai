package com.exemple.ai.assistant.core;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.ollama.OllamaContainer;

import com.exemple.ai.assistant.core.ollama.EmbeddedOllamaConfiguration;
import com.exemple.ai.assistant.core.ollama.EmbeddedOllamaConfigurationProperties;

import jakarta.annotation.PostConstruct;

@Configuration
@Import({ AssistantConfiguration.class, EmbeddedOllamaConfiguration.class })
public class AssistantTestConfiguration {

    @Autowired
    private OllamaContainer embeddedOllama;

    @Autowired
    private EmbeddedOllamaConfigurationProperties embeddedOllamaProperties;

    @Bean
    public DynamicPropertyRegistrar applicationProperties(OllamaContainer embeddedOllama) {
        return registry -> registry.add("spring.ai.ollama.base-url", embeddedOllama::getEndpoint);
    }

    @PostConstruct
    private void initOllama() throws IOException, InterruptedException {
        embeddedOllama.execInContainer("ollama", "pull", embeddedOllamaProperties.model());
        embeddedOllama.commitToImage("ollama/ollama/" + embeddedOllamaProperties.model() + ":" + embeddedOllamaProperties.version());
    }

}
