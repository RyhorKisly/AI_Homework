package com.example.jsonplaceholderclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the JSONPlaceholder Clone project.
 * This is a Spring Boot application that provides a REST API for managing users.
 *
 * @author Ryhor Kisly
 * @version 1.0
 */
@SpringBootApplication
public class JsonPlaceholderCloneApplication {
    /**
     * Main method that starts the Spring Boot application.
     *
     * @param args Command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(JsonPlaceholderCloneApplication.class, args);
    }
} 