CREATE TABLE image (
    id INT PRIMARY KEY AUTO_INCREMENT,
    image LONGBLOB,
    orderId VARCHAR(20),
    description VARCHAR(60)
);