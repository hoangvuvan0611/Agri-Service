-- Tạo bảng user va index
CREATE TABLE IF NOT EXISTS descriptions (
    id BIGINT PRIMARY KEY,
    product_id BIGINT NOT NULL REFERENCES products(id),
    certificate TEXT,                                   -- Giay chung nhan
    origin VARCHAR(100),                                -- Nguon goc
    uses TEXT,                                          -- Cong dung, chi tiet ve san pham
    instructions_for_use TEXT,                          -- Huong dan su dung
    preserving_instruction TEXT,                        -- Huong dan bao quan
    expiry VARCHAR(50),                                 -- Thoi gian het han
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50)
);