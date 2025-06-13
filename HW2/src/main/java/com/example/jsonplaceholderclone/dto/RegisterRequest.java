package com.example.jsonplaceholderclone.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data Transfer Object for user registration requests.
 * Contains the information required to create a new user account.
 *
 * @author Your Name
 * @version 1.0
 */
@Data
public class RegisterRequest {
    /**
     * Username for the new account.
     * Must be between 3 and 20 characters and not blank.
     */
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    /**
     * Email address for the new account.
     * Must be a valid email format, not blank, and maximum 50 characters.
     */
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    /**
     * Password for the new account.
     * Must be between 6 and 40 characters and not blank.
     */
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
} 