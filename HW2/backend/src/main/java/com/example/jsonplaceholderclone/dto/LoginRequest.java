package com.example.jsonplaceholderclone.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Data Transfer Object for user login requests.
 * Contains the credentials required for authentication.
 *
 * @author Your Name
 * @version 1.0
 */
@Data
public class LoginRequest {
    /**
     * Username for authentication.
     * Must not be blank.
     */
    @NotBlank
    private String username;

    /**
     * Password for authentication.
     * Must not be blank.
     */
    @NotBlank
    private String password;
} 