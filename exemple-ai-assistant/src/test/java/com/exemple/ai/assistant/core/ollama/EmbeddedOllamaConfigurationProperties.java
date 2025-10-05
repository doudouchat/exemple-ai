package com.exemple.ai.assistant.core.ollama;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "ollama")
public record EmbeddedOllamaConfigurationProperties(@DefaultValue("ollama/ollama") String image,
                                                    String version,
                                                    String model) {
}
