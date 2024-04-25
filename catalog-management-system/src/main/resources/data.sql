CREATE DATABASE catalog_management_system;

USE catalog_management_system;


CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(255),
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    category VARCHAR(255),
    date_added DATE DEFAULT CURRENT_DATE
);


INSERT INTO product (name, brand, description, price, quantity, category, date_added)
VALUES 
    ('Product 1', 'Brand A', 'Description of Product 1', 10.99, 100, 'Category A', '2024-04-25'),
    ('Product 2', 'Brand B', 'Description of Product 2', 20.49, 50, 'Category B', '2024-04-25'),
    ('Product 3', 'Brand C', 'Description of Product 3', 5.99, 200, 'Category A', '2024-04-25');
