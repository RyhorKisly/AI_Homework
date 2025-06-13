package com.example.jsonplaceholderclone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Embeddable class representing geographical coordinates.
 * This class is embedded within the Address entity.
 *
 * @author Your Name
 * @version 1.0
 */
@Data
@Embeddable
public class Geo {
    /**
     * Latitude coordinate.
     */
    @Column(name = "geo_lat")
    private String lat;
    
    /**
     * Longitude coordinate.
     */
    @Column(name = "geo_lng")
    private String lng;
} 