DROP TABLE IF EXISTS products;
CREATE TABLE products
(
    name        VARCHAR(255) PRIMARY KEY,
    type        VARCHAR(255) NOT NULL,
    description TEXT,
    stock       INT Default 0 check (stock >= 0)
);