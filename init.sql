CREATE DATABASE orderservice;
GRANT ALL PRIVILEGES ON DATABASE orderservice to root;

CREATE DATABASE inventoryservice;
GRANT ALL PRIVILEGES ON DATABASE inventoryservice to root;

\c inventoryservice;

CREATE TABLE IF NOT EXISTS inventory (
  id INT PRIMARY KEY,
  sku_code TEXT NOT NULL,
  stock INT NOT NULL
);

INSERT INTO inventory(id, sku_code, stock) VALUES (1, 'GAME1', 10);
INSERT INTO inventory(id, sku_code, stock) VALUES (2, 'GAME2', 50);
INSERT INTO inventory(id, sku_code, stock) VALUES (3, 'GAME3', 0);
INSERT INTO inventory(id, sku_code, stock) VALUES (4, 'GAME4', 1);