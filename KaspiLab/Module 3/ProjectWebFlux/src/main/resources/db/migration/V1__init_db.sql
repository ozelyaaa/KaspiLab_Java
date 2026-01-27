CREATE TABLE product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(10, 2) NOT NULL
);

CREATE TABLE delivery (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    address VARCHAR(500) NOT NULL,
    status VARCHAR(50) NOT NULL
);

INSERT INTO product (name, price) VALUES
    ('Lenovo Yoga S940', 589990.00),
    ('Xiaomi Note 18 Pro', 289990.00),
    ('Samsung Galaxy S25', 529990.00),
    ('iPhone 17 Pro Max', 789990.00);

INSERT INTO delivery (product_id, address, status) VALUES
    (1, 'Almaty, Abay Ave 10', 'CREATED'),
    (2, 'Astana, Turan Ave 25', 'IN_PROGRESS'),
    (3, 'Shymkent, Tauke Khan St 5', 'DELIVERED');