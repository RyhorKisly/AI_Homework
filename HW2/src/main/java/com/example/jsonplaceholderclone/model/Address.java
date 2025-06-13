package com.example.jsonplaceholderclone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

/**
 * Embeddable class representing a user's address.
 * This class is embedded within the User entity.
 *
 * @author Your Name
 * @version 1.0
 */
@Data
@Embeddable
public class Address {
    /**
     * Street address of the user.
     */
    @Column(name = "street")
    private String street;
    
    /**
     * Suite or apartment number.
     */
    @Column(name = "suite")
    private String suite;
    
    /**
     * City name.
     */
    @Column(name = "city")
    private String city;
    
    /**
     * ZIP or postal code.
     */
    @Column(name = "zipcode")
    private String zipcode;
    
    /**
     * Geographical coordinates of the address.
     * Embedded object containing latitude and longitude.
     */
    @Embedded
    private Geo geo;
} 