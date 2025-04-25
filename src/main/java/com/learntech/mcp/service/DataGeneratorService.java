package com.learntech.mcp.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import com.learntech.mcp.connector.DataGeneratorConnector;
import com.learntech.mcp.model.User;

/**
 * DataGeneratorService
 *
 * @author Uthiraraj Saminathan
 */
@Service
public class DataGeneratorService {

    private DataGeneratorConnector dataGeneratorConnector;

    public DataGeneratorService(DataGeneratorConnector dataGeneratorConnector) {
        this.dataGeneratorConnector = dataGeneratorConnector;
    }

    @Tool(
            name = "generateData",
            description = "Generates data based on the provided prompt."
    )
    public String generateData(String prompt) {
        return "Generated data for " + prompt;
    }

    @Tool(
            name = "getUserData",
            description = "Generate User data based on the provided id and age."
    )
    public User getUserData(String id, int age) {
        return dataGeneratorConnector.getUserData(id,  age);
    }

}
