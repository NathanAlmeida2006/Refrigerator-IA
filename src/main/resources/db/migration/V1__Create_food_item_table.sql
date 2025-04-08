CREATE TABLE food_item (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           category VARCHAR(50) NOT NULL,
                           amount INT NOT NULL,
                           expiration_date TIMESTAMP NOT NULL
);