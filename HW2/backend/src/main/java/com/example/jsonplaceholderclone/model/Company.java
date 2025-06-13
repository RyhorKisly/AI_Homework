package com.example.jsonplaceholderclone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Embeddable class representing a company.
 * This class is embedded within the User entity.
 *
 * @author Your Name
 * @version 1.0
 */
@Data
@Embeddable
public class Company {
    /**
     * Name of the company.
     */
    @Column(name = "company_name")
    private String name;
    
    /**
     * Company's catchphrase or slogan.
     */
    @Column(name = "company_catch_phrase")
    private String catchPhrase;
    
    /**
     * Business description or mission statement.
     */
    @Column(name = "company_bs")
    private String bs;
} 