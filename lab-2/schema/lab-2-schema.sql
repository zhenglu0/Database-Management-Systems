CREATE TABLE users (
  user_id BIGSERIAL PRIMARY KEY,
  username TEXT UNIQUE,
  password TEXT
);

CREATE TABLE books (
  isbn TEXT PRIMARY KEY,
  title TEXT,
  author TEXT,
  price INTEGER
);

CREATE TABLE shopping_carts (
  user_id BIGINT REFERENCES users(user_id) PRIMARY KEY,
  modification_timestamp TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE shopping_cart_items (
  cart_id BIGINT REFERENCES shopping_carts(user_id),
  isbn TEXT REFERENCES books(isbn),
  quantity INTEGER,
  CONSTRAINT shopping_cart_items_key
    PRIMARY KEY (cart_id, isbn)
);
