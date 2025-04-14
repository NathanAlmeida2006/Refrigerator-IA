CREATE TABLE recipe
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    instructions TEXT         NOT NULL
);