CREATE DATABASE IF NOT EXISTS cats_food_shop;
CREATE TABLE IF NOT EXISTS  product(
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	price FLOAT NOT NULL,
	count INT NOT NULL
);
CREATE TABLE IF NOT EXISTS  client(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(30) NOT NULL
);
CREATE TABLE IF NOT EXISTS  orders(
    id SERIAL PRIMARY KEY,
    client_id INT  ,
    date TIMESTAMP,
    total_price FLOAT,
    FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS  order_product(
    order_id INT,
    product_id INT,
    count INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id),
);