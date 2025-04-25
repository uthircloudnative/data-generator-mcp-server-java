# Data Generator Java MCP Server

A Spring Boot application that implements a Model Completion Protocol (MCP) server for data generation. This project demonstrates how to create a custom MCP server using Spring AI that can be integrated with AI models to generate data based on prompts.

## Overview

This application serves as an MCP server that provides tools for data generation. It uses Spring AI's MCP server capabilities to expose Java methods as tools that can be called by AI models. The server is configured to run in synchronous mode.

## Features

- Implements the Model Completion Protocol (MCP) server
- Provides tools for data generation
- Supports synchronous execution mode
- Includes a REST client for external API integration
- Simple User data model implementation

## Prerequisites

- Java 21 or higher
- Maven 3.9+ (or use the included Maven wrapper)
- Basic understanding of Spring Boot and Spring AI

## Getting Started

### Building the Application

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd data-generator-java-mcp-server
   ```

2. Build the application using Maven:
   ```bash
   ./mvnw clean install
   ```

### Running the Application

Run the application using the Maven wrapper:

```bash
./mvnw spring-boot:run
```

The MCP server will start and be ready to accept requests.

## Configuration

### MCP Server Configuration

The MCP server is configured in the `application.properties` file:

```properties
# Application name
spring.application.name=data-generator-java-mcp-server

# MCP server name
spring.ai.mcp.server.name=data-generator-java-mcp-server

# MCP server type (SYNC or ASYNC)
spring.ai.mcp.server.type=SYNC

# Disable Spring Boot banner
spring.main.banner-mode=off

# Run as a non-web application
spring.main.web-application-type=none
```

### Custom Configuration

You can customize the MCP server by modifying the `McpServerConfig` class:

```java
@Configuration
public class McpServerConfig {

    @Bean
    public ToolCallbackProvider dataGeneratorToolCallbackProvider(DataGeneratorService dataGeneratorService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(dataGeneratorService)
                .build();
    }
}
```

## Available Tools

The MCP server exposes the following tools:

1. **generateData** - Generates data based on a provided prompt
   - Parameter: `prompt` (String)
   - Returns: Generated data as a String

2. **getUserData** - Generates user data based on provided ID and age
   - Parameters:
     - `id` (String)
     - `age` (int)
   - Returns: User object with ID, name, and age

## Extending the Application

To add new tools to the MCP server:

1. Create a new service class or extend the existing `DataGeneratorService`
2. Add methods annotated with `@Tool` annotation
3. Register the service in the `McpServerConfig` class

Example:

```java
@Service
public class MyCustomService {

    @Tool(
        name = "myCustomTool",
        description = "Description of what the tool does"
    )
    public SomeReturnType myCustomTool(String param1, int param2) {
        // Implementation
        return result;
    }
}
```

Then register it in the configuration:

```java
@Bean
public ToolCallbackProvider myCustomToolCallbackProvider(MyCustomService myCustomService) {
    return MethodToolCallbackProvider.builder()
            .toolObjects(myCustomService)
            .build();
}
```

## Integration with AI Models

This MCP server can be integrated with AI models that support the Model Completion Protocol. The AI model can call the tools exposed by this server to generate data as part of its processing.
