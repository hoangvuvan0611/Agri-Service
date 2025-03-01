CREATE TYPE role_enum AS ENUM ('SUPERVISOR', 'ADMIN', 'STAFF');
CREATE TYPE status_enum AS ENUM ('ACTIVE', 'DEACTIVATE', 'EXPIRED');
CREATE TYPE order_status_enum AS ENUM (
    'PENDING',
    'PROCESSING',
    'SHIPPED',
    'DELIVERED',
    'CANCELLED',
    'RETURNED',
    'REFUNDED',
    'ON_HOLD',
    'COMPLETED',
    'FAILED'
);
CREATE TYPE assets_type_enum AS ENUM ('IMAGE', 'VIDEO', 'DOCUMENT');
CREATE TYPE coupon_type_enum AS ENUM ('PERCENT', 'FIXED_AMOUNT');

-- Tạo bảng categories và index
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT PRIMARY KEY,
    parent_id BIGINT REFERENCES categories(id),
    name VARCHAR(100) NOT NULL,
    slug TEXT,
    description TEXT,
    image TEXT,
    status status_enum NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    UNIQUE (slug)
);
CREATE INDEX idx_categories_name ON categories(name);
CREATE INDEX idx_categories_parent_id ON categories(parent_id);
CREATE INDEX idx_categories_status ON categories(status);

-- Tạo bảng products và index
CREATE TABLE IF NOT EXISTS products (
    id BIGINT PRIMARY KEY,
    category_id BIGINT NOT NULL REFERENCES categories(id),
    name VARCHAR(100) NOT NULL,
    slug TEXT,
    description TEXT,
    price MONEY NOT NULL DEFAULT 0,
    discount DECIMAL NOT NULL DEFAULT 0,
    quantity INTEGER NOT NULL DEFAULT 0,
    sold INTEGER NOT NULL DEFAULT 0,
    status status_enum NOT NULL,
    featured BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    UNIQUE (slug)
);
CREATE INDEX idx_products_name ON products(name);
CREATE INDEX idx_products_category_id ON products(category_id);
CREATE INDEX idx_products_status ON products(status);
CREATE INDEX idx_products_featured ON products(featured);

-- Tạo bảng cities và index
CREATE TABLE IF NOT EXISTS cities (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    postal_code VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    UNIQUE (postal_code)
);
CREATE INDEX idx_cities_name ON cities(name);

-- Tạo bảng districts và index
CREATE TABLE IF NOT EXISTS districts (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100),
    postal_code VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    UNIQUE (postal_code)
);
CREATE INDEX idx_districts_name ON districts(name);

-- Tạo bảng wards và index
CREATE TABLE IF NOT EXISTS wards (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    postal_code VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    UNIQUE (postal_code)
);
CREATE INDEX idx_wards_name ON wards(name);

-- Tạo bảng customers và index
CREATE TABLE IF NOT EXISTS customers (
    id BIGINT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    firstname VARCHAR(100),
    lastname VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100),
    address VARCHAR(100),
    city_id BIGINT REFERENCES cities(id),
    district_id BIGINT REFERENCES districts(id),
    phone_number VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (username),
    UNIQUE (email)
);
CREATE INDEX idx_customers_phone ON customers(phone_number);
CREATE INDEX idx_customers_city ON customers(city_id);
CREATE INDEX idx_customers_district ON customers(district_id);

-- Tạo bảng assets và index
CREATE TABLE IF NOT EXISTS assets (
    id BIGINT PRIMARY KEY,
    filename VARCHAR(50) NOT NULL,
    path VARCHAR(100),
    type assets_type_enum NOT NULL,
    size BIGINT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_assets_type ON assets(type);

-- Tạo bảng product_asset và index
CREATE TABLE IF NOT EXISTS product_asset (
    id BIGINT PRIMARY KEY,
    product_id BIGINT NOT NULL REFERENCES products(id),
    asset_id BIGINT NOT NULL REFERENCES assets(id),
    type VARCHAR(50) NOT NULL
);
CREATE INDEX idx_product_asset_product ON product_asset(product_id);
CREATE INDEX idx_product_asset_asset ON product_asset(asset_id);

-- Tạo bảng carts và index
CREATE TABLE IF NOT EXISTS carts (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT NOT NULL REFERENCES customers(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_carts_customer ON carts(customer_id);

-- Tạo bảng cart_items và index
CREATE TABLE IF NOT EXISTS cart_items (
    id BIGINT PRIMARY KEY,
    cart_id BIGINT NOT NULL REFERENCES carts(id),
    product_id BIGINT NOT NULL REFERENCES products(id),
    quantity BIGINT NOT NULL DEFAULT 0,
    price MONEY DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_cart_items_cart ON cart_items(cart_id);
CREATE INDEX idx_cart_items_product ON cart_items(product_id);

-- Tạo bảng coupons và index
CREATE TABLE IF NOT EXISTS coupons (
    id BIGINT PRIMARY KEY,
    coupon_code VARCHAR(50),
    coupon_type coupon_type_enum,
    coupon_value money DEFAULT 0,
    coupon_start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    coupon_end_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    coupon_min_spend MONEY DEFAULT 0,
    coupon_max_spend MONEY DEFAULT 0,
    coupon_uses_per_customer BIGINT DEFAULT 0,
    coupon_uses_per_coupon BIGINT DEFAULT 0,
    coupon_status status_enum,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    UNIQUE (coupon_code)
);
CREATE INDEX idx_coupons_status ON coupons(coupon_status);
CREATE INDEX idx_coupons_dates ON coupons(coupon_start_time, coupon_end_time);

-- Tạo bảng affiliates và index
CREATE TABLE IF NOT EXISTS affiliates (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT REFERENCES customers(id),
    code VARCHAR(50),
    commision MONEY,
    balance MONEY,
    is_active BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (code)
);
CREATE INDEX idx_affiliates_customer ON affiliates(customer_id);
CREATE INDEX idx_affiliates_active ON affiliates(is_active);

-- Tạo bảng delivery_info và index
CREATE TABLE IF NOT EXISTS delivery_info (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT REFERENCES customers(id),
    phone_number VARCHAR(50),
    address VARCHAR(100),
    city_id BIGINT REFERENCES cities(id),
    district_id BIGINT REFERENCES districts(id),
    ward_id BIGINT REFERENCES wards(id)
);
CREATE INDEX idx_delivery_customer ON delivery_info(customer_id);
CREATE INDEX idx_delivery_phone ON delivery_info(phone_number);

-- Tạo bảng orders và index
CREATE TABLE IF NOT EXISTS orders (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT NOT NULL REFERENCES customers(id),
    status order_status_enum,
    shipping_fee MONEY,
    delivery_info_id BIGINT REFERENCES delivery_info(id),
    total_fee MONEY,
    payment_id BIGINT,
    coupon_id BIGINT REFERENCES coupons(id),
    affiliate_id BIGINT REFERENCES affiliates(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    canceled_at TIMESTAMP,
    completed_at TIMESTAMP,
    delivery_at TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_orders_customer ON orders(customer_id);
CREATE INDEX idx_orders_status ON orders(status);
CREATE INDEX idx_orders_dates ON orders(created_at, completed_at);
CREATE INDEX idx_orders_delivery ON orders(delivery_info_id);
CREATE INDEX idx_orders_coupon ON orders(coupon_id);
CREATE INDEX idx_orders_affiliate ON orders(affiliate_id);

-- Tạo bảng order_items và index
CREATE TABLE IF NOT EXISTS order_items (
    id BIGINT PRIMARY KEY,
    order_id BIGINT NOT NULL REFERENCES orders(id),
    product_id BIGINT NOT NULL REFERENCES products(id),
    name VARCHAR(100) NOT NULL,
    quantity BIGINT DEFAULT 0,
    price MONEY DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_order_items_order ON order_items(order_id);
CREATE INDEX idx_order_items_product ON order_items(product_id);

-- Tạo bảng reviews và index
CREATE TABLE IF NOT EXISTS reviews (
    id BIGINT PRIMARY KEY,
    product_id BIGINT NOT NULL REFERENCES products(id),
    customer_id BIGINT NOT NULL REFERENCES customers(id),
    rating SMALLINT,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    is_approved BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_reviews_product ON reviews(product_id);
CREATE INDEX idx_reviews_customer ON reviews(customer_id);
CREATE INDEX idx_reviews_rating ON reviews(rating);
CREATE INDEX idx_reviews_approved ON reviews(is_approved);


-- Tạo bảng user va index
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    full_name VARCHAR(100),
    date_of_birth DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    UNIQUE (username)
);
CREATE INDEX idx_users_username ON users(username);

CREATE TABLE IF NOT EXISTS roles (
    id BIGINT PRIMARY KEY,
    name role_enum NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS users_roles (
    user_id BIGINT REFERENCES users(id),
    role_id BIGINT REFERENCES roles(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50),
    PRIMARY KEY (user_id, role_id)
);

-- Tạo bảng attributes và index
CREATE TABLE IF NOT EXISTS attributes (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT
);
CREATE INDEX idx_attributes_name ON attributes(name);