CREATE TABLE books (
  book_id BIGSERIAL PRIMARY KEY,
  isbn TEXT NOT NULL,
  title TEXT NOT NULL,
  author TEXT NOT NULL,
  price INTEGER NOT NULL
);

CREATE TABLE shopping_carts (
  cart_id BIGSERIAL PRIMARY KEY,
  modification_timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE users (
  user_id BIGSERIAL PRIMARY KEY,
  username TEXT UNIQUE NOT NULL,
  password TEXT NOT NULL,
  cart_id BIGINT REFERENCES shopping_carts(cart_id)
);

CREATE TABLE shopping_cart_items (
  item_id BIGSERIAL PRIMARY KEY,
  cart_id BIGINT REFERENCES shopping_carts(cart_id) NOT NULL,
  book_id BIGINT REFERENCES books(book_id) NOT NULL,
  quantity INTEGER NOT NULL
);
