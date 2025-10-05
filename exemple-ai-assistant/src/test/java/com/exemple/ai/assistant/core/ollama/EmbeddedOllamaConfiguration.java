package com.exemple.ai.assistant.core.ollama;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.ollama.OllamaContainer;
import org.testcontainers.utility.DockerImageName;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableConfigurationProperties(EmbeddedOllamaConfigurationProperties.class)
@Slf4j
public class EmbeddedOllamaConfiguration {

    @Bean
    public OllamaContainer embeddedOllama(EmbeddedOllamaConfigurationProperties properties) {
        return new OllamaContainer(DockerImageName.parse(properties.image() + ":" + properties.version()).asCompatibleSubstituteFor("ollama/ollama"))
                .withCreateContainerCmdModifier(
                        cmd -> cmd.getHostConfig().withDeviceRequests(null))
                .withLogConsumer(new Slf4jLogConsumer(LOG));
    }
}
