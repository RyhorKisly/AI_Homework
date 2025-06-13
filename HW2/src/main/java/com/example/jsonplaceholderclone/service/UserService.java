package com.example.jsonplaceholderclone.service;

import com.example.jsonplaceholderclone.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service interface for managing User entities.
 * Provides methods for CRUD operations and user-specific queries.
 *
 * @author Your Name
 * @version 1.0
 */
public interface UserService {
    /**
     * Retrieves a paginated list of all users.
     *
     * @param pageable Pagination information
     * @return A page of users
     */
    Page<User> findAll(Pageable pageable);

    /**
     * Retrieves a user by their ID.
     *
     * @param id The user's ID
     * @return An Optional containing the user if found, empty otherwise
     */
    Optional<User> findById(Long id);

    /**
     * Retrieves a user by their email address.
     *
     * @param email The user's email address
     * @return An Optional containing the user if found, empty otherwise
     */
    Optional<User> findByEmail(String email);

    /**
     * Retrieves a user by their username.
     *
     * @param username The user's username
     * @return An Optional containing the user if found, empty otherwise
     */
    Optional<User> findByUsername(String username);

    /**
     * Saves a user entity.
     *
     * @param user The user to save
     * @return The saved user entity
     */
    User save(User user);

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to delete
     */
    void deleteById(Long id);

    /**
     * Checks if a user exists with the given email address.
     *
     * @param email The email address to check
     * @return true if a user exists with the email, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Checks if a user exists with the given username.
     *
     * @param username The username to check
     * @return true if a user exists with the username, false otherwise
     */
    boolean existsByUsername(String username);
} 