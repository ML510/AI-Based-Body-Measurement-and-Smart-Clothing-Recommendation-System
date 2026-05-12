CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    time TIME,
    description VARCHAR(300),
    gender VARCHAR(20),
    net_total DOUBLE,
    customer_id VARCHAR(20)
);