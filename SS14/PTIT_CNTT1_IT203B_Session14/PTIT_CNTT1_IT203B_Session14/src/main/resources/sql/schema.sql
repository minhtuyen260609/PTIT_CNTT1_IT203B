-- Tạo database
CREATE DATABASE IF NOT EXISTS FlashSaleDB
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE FlashSaleDB;

-- Bảng Users
CREATE TABLE IF NOT EXISTS Users (
    user_id     INT AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(100)    NOT NULL UNIQUE,
    email       VARCHAR(150)    NOT NULL UNIQUE,
    created_at  DATETIME        DEFAULT CURRENT_TIMESTAMP
);

-- Bảng Products
CREATE TABLE IF NOT EXISTS Products (
    product_id      INT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(200)    NOT NULL,
    category        VARCHAR(100)    NOT NULL,
    price           DECIMAL(15, 2)  NOT NULL,
    stock_quantity  INT             NOT NULL DEFAULT 0,
    CONSTRAINT chk_price    CHECK (price > 0),
    CONSTRAINT chk_stock    CHECK (stock_quantity >= 0)
);

-- Bảng Orders
CREATE TABLE IF NOT EXISTS Orders (
    order_id    INT AUTO_INCREMENT PRIMARY KEY,
    user_id     INT             NOT NULL,
    order_date  DATETIME        DEFAULT CURRENT_TIMESTAMP,
    status      VARCHAR(50)     DEFAULT 'SUCCESS',
    CONSTRAINT fk_orders_user
        FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Bảng Order_Details
CREATE TABLE IF NOT EXISTS Order_Details (
    detail_id   INT AUTO_INCREMENT PRIMARY KEY,
    order_id    INT             NOT NULL,
    product_id  INT             NOT NULL,
    quantity    INT             NOT NULL,
    unit_price  DECIMAL(15, 2)  NOT NULL,
    CONSTRAINT fk_detail_order
        FOREIGN KEY (order_id)   REFERENCES Orders(order_id),
    CONSTRAINT fk_detail_product
        FOREIGN KEY (product_id) REFERENCES Products(product_id),
    CONSTRAINT chk_quantity     CHECK (quantity > 0),
    CONSTRAINT chk_unit_price   CHECK (unit_price > 0)
);
