package com.example.jsonplaceholderclone.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Entity class representing a user in the system.
 * This class maps to the 'users' table in the database.
 *
 * @author Your Name
 * @version 1.0
 */
@Data
@Entity
@Table(name = "users")
public class User {
    /**
     * Unique identifier for the user.
     * Automatically generated using identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Unique username for the user.
     * Must not be blank.
     */
    @NotBlank
    @Column(unique = true)
    private String username;
    
    /**
     * Hashed password for the user.
     * Must not be blank.
     */
    @NotBlank
    @Column(name = "password_hash")
    private String passwordHash;
    
    /**
     * Full name of the user.
     * Must not be blank.
     */
    @NotBlank
    @Column(name = "name")
    private String name;
    
    /**
     * Email address of the user.
     * Must be a valid email format and not blank.
     */
    @NotBlank
    @Email
    private String email;
    
    /**
     * Address information for the user.
     * Embedded object containing street, city, etc.
     */
    @Embedded
    private Address address;
    
    /**
     * Phone number of the user.
     * Must not be blank.
     */
    @NotBlank
    private String phone;
    
    /**
     * Website URL of the user.
     * Must not be blank.
     */
    @NotBlank
    private String website;
    
    /**
     * Company information for the user.
     * Embedded object containing company details.
     */
    @Embedded
    private Company company;
} 