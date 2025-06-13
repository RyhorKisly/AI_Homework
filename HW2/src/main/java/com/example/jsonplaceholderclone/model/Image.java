package com.example.jsonplaceholderclone.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class representing an image in the system.
 * This class maps to the 'images' table in the database.
 *
 * @author Your Name
 * @version 1.0
 */
@Data
@Entity
@Table(name = "images")
public class Image {
    /**
     * Unique identifier for the image.
     * Automatically generated using identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Original filename of the image.
     */
    @Column(nullable = false)
    private String filename;

    /**
     * Content type of the image (e.g., image/jpeg, image/png).
     */
    @Column(nullable = false)
    private String contentType;

    /**
     * Size of the image in bytes.
     */
    @Column(nullable = false)
    private Long size;

    /**
     * The user who owns this image.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
} 