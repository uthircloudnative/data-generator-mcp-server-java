package com.learntech.mcp.config;

import com.learntech.mcp.service.DataGeneratorService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * McpServerConfig
 *
 * @author Uthiraraj Saminathan
 */
@Configuration
public class McpServerConfig {

    @Bean
    public ToolCallbackProvider dataGeneratorToolCallbackProvider(DataGeneratorService dataGeneratorService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(dataGeneratorService)
                .build();
    }

}
