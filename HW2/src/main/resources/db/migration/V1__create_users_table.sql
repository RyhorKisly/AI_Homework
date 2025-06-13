CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    website VARCHAR(255) NOT NULL,
    street VARCHAR(255),
    suite VARCHAR(255),
    city VARCHAR(255),
    zipcode VARCHAR(255),
    geo_lat VARCHAR(50),
    geo_lng VARCHAR(50),
    company_name VARCHAR(255),
    company_catch_phrase TEXT,
    company_bs VARCHAR(255)
); 