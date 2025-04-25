package com.learntech.mcp.connector;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.learntech.mcp.model.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class DataGeneratorConnector {

    private final RestTemplate restTemplate;

    public DataGeneratorConnector() {
        this.restTemplate = new RestTemplate();
    }

    public void fetchCsvFromApi(String apiUrl, String outputFilePath) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                    writer.write(response.getBody());
                }
            } else {
                throw new RuntimeException("Failed to fetch CSV: " + response.getStatusCode());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing CSV to file", e);
        }
    }

    // Define a method it reatuns a simple object with id, name and age
    public User getUserData(String id,  int age) {
        return new User("5", "John Doe", 45);
    }
}
