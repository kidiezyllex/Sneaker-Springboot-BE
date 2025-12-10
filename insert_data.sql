SET FOREIGN_KEY_CHECKS = 0;

-- ============================================
-- 1. ACCOUNTS (15+ rows)
-- ============================================
INSERT INTO accounts (code, full_name, phone_number, email, password, birthday, gender, avatar, role, citizen_id, status, created_at, updated_at) VALUES
('ACC001', 'Nguyễn Văn Anh', '0901234567', 'nguyenvananh@gmail.com', '$2a$10$l1NcsDjlCaBIsVX4SNg1FeKfUvqWKejOkX3JGYLIYKfF1Li80GZDW', '1990-01-15 00:00:00', 1, NULL, 'ADMIN', '001234567890', 'ACTIVE', NOW(), NOW()),
('ACC002', 'Trần Thị Bình', '0901234568', 'tranthibinh@gmail.com', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '1995-05-20 00:00:00', 0, NULL, 'CUSTOMER', '001234567891', 'ACTIVE', NOW(), NOW()),
('ACC003', 'Lê Văn Cường', '0901234569', 'levancuong@gmail.com', '$2a$10$rx51w6Fzu07iiMqEif2K3u5rzhO05TvaOusM31.Iq6.pajDkgDd7i', '1992-08-10 00:00:00', 1, NULL, 'STAFF', '001234567892', 'ACTIVE', NOW(), NOW()),
('ACC004', 'Phạm Quốc Dũng', '0901111111', 'phamquocdung@gmail.com', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '1993-03-12 00:00:00', 1, NULL, 'CUSTOMER', '001111111111', 'ACTIVE', NOW(), NOW()),
('ACC005', 'Hoàng Thị Giang', '0902222222', 'hoangthigiang@gmail.com', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '1994-07-25 00:00:00', 0, NULL, 'CUSTOMER', '002222222222', 'ACTIVE', NOW(), NOW()),
('ACC006', 'Vũ Văn Hùng', '0903333333', 'vuvanhung@gmail.com', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '1991-11-08 00:00:00', 1, NULL, 'CUSTOMER', '003333333333', 'ACTIVE', NOW(), NOW()),
('ACC007', 'Đặng Thị Linh', '0904444444', 'dangthilinh@gmail.com', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '1996-02-14 00:00:00', 0, NULL, 'CUSTOMER', '004444444444', 'ACTIVE', NOW(), NOW()),
('ACC008', 'Bùi Văn Minh', '0905555555', 'buivanminh@gmail.com', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '1990-09-30 00:00:00', 1, NULL, 'CUSTOMER', '005555555555', 'ACTIVE', NOW(), NOW()),
('ACC009', 'Ngô Thị Ngọc', '0906666666', 'ngothingoc@gmail.com', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '1997-04-18 00:00:00', 0, NULL, 'CUSTOMER', '006666666666', 'ACTIVE', NOW(), NOW()),
('ACC010', 'Đỗ Văn Phong', '0907777777', 'dovanphong@gmail.com', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '1992-12-05 00:00:00', 1, NULL, 'CUSTOMER', '007777777777', 'ACTIVE', NOW(), NOW()),
('ACC011', 'Hà Thị Quỳnh', '0908888888', 'hathiquynh@gmail.com', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '1995-06-22 00:00:00', 0, NULL, 'CUSTOMER', '008888888888', 'ACTIVE', NOW(), NOW()),
('ACC012', 'Lý Văn Sơn', '0909999999', 'lyvanson@gmail.com', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '1993-10-15 00:00:00', 1, NULL, 'CUSTOMER', '009999999999', 'ACTIVE', NOW(), NOW()),
('ACC013', 'Mai Thị Thảo', '0910000000', 'maithithao@gmail.com', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '1994-01-28 00:00:00', 0, NULL, 'CUSTOMER', '010000000000', 'ACTIVE', NOW(), NOW()),
('ACC014', 'Trịnh Văn Tùng', '0911111111', 'trinhvantung@gmail.com', '$2a$10$rx51w6Fzu07iiMqEif2K3u5rzhO05TvaOusM31.Iq6.pajDkgDd7i', '1991-05-10 00:00:00', 1, NULL, 'STAFF', '011111111111', 'ACTIVE', NOW(), NOW()),
('ACC015', 'Phan Văn Khánh', '0912222222', 'phanvankhanh@gmail.com', '$2a$10$l1NcsDjlCaBIsVX4SNg1FeKfUvqWKejOkX3JGYLIYKfF1Li80GZDW', '1988-03-20 00:00:00', 1, NULL, 'ADMIN', '012222222222', 'ACTIVE', NOW(), NOW());

-- ============================================
-- 2. BRANDS (12+ rows)
-- ============================================
INSERT INTO brands (name, status, created_at, updated_at) VALUES
('Nike', 'ACTIVE', NOW(), NOW()),
('Adidas', 'ACTIVE', NOW(), NOW()),
('Puma', 'ACTIVE', NOW(), NOW()),
('New Balance', 'ACTIVE', NOW(), NOW()),
('Converse', 'ACTIVE', NOW(), NOW()),
('Vans', 'ACTIVE', NOW(), NOW()),
('Reebok', 'ACTIVE', NOW(), NOW()),
('Under Armour', 'ACTIVE', NOW(), NOW()),
('Jordan', 'ACTIVE', NOW(), NOW()),
('Asics', 'ACTIVE', NOW(), NOW()),
('Fila', 'ACTIVE', NOW(), NOW()),
('Skechers', 'ACTIVE', NOW(), NOW());

-- ============================================
-- 3. CATEGORIES (12+ rows)
-- ============================================
INSERT INTO categories (name, status, created_at, updated_at) VALUES
('Running Shoes', 'ACTIVE', NOW(), NOW()),
('Basketball Shoes', 'ACTIVE', NOW(), NOW()),
('Casual Shoes', 'ACTIVE', NOW(), NOW()),
('Sneakers', 'ACTIVE', NOW(), NOW()),
('Training Shoes', 'ACTIVE', NOW(), NOW()),
('Lifestyle Shoes', 'ACTIVE', NOW(), NOW()),
('High-Top Shoes', 'ACTIVE', NOW(), NOW()),
('Low-Top Shoes', 'ACTIVE', NOW(), NOW()),
('Slip-On Shoes', 'ACTIVE', NOW(), NOW()),
('Athletic Shoes', 'ACTIVE', NOW(), NOW()),
('Walking Shoes', 'ACTIVE', NOW(), NOW()),
('Skateboarding Shoes', 'ACTIVE', NOW(), NOW());

-- ============================================
-- 4. COLORS (15+ rows)
-- ============================================
INSERT INTO colors (name, code, status, created_at, updated_at) VALUES
('Black', '#000000', 'ACTIVE', NOW(), NOW()),
('White', '#FFFFFF', 'ACTIVE', NOW(), NOW()),
('Red', '#FF0000', 'ACTIVE', NOW(), NOW()),
('Blue', '#0000FF', 'ACTIVE', NOW(), NOW()),
('Green', '#008000', 'ACTIVE', NOW(), NOW()),
('Yellow', '#FFFF00', 'ACTIVE', NOW(), NOW()),
('Gray', '#808080', 'ACTIVE', NOW(), NOW()),
('Navy', '#000080', 'ACTIVE', NOW(), NOW()),
('Pink', '#FFC0CB', 'ACTIVE', NOW(), NOW()),
('Orange', '#FFA500', 'ACTIVE', NOW(), NOW()),
('Purple', '#800080', 'ACTIVE', NOW(), NOW()),
('Brown', '#A52A2A', 'ACTIVE', NOW(), NOW()),
('Beige', '#F5F5DC', 'ACTIVE', NOW(), NOW()),
('Silver', '#C0C0C0', 'ACTIVE', NOW(), NOW()),
('Gold', '#FFD700', 'ACTIVE', NOW(), NOW());

-- ============================================
-- 5. MATERIALS (12+ rows)
-- ============================================
INSERT INTO materials (name, status, created_at, updated_at) VALUES
('Leather', 'ACTIVE', NOW(), NOW()),
('Synthetic Leather', 'ACTIVE', NOW(), NOW()),
('Mesh', 'ACTIVE', NOW(), NOW()),
('Canvas', 'ACTIVE', NOW(), NOW()),
('Rubber', 'ACTIVE', NOW(), NOW()),
('Knit', 'ACTIVE', NOW(), NOW()),
('Suede', 'ACTIVE', NOW(), NOW()),
('Nubuck', 'ACTIVE', NOW(), NOW()),
('Primeknit', 'ACTIVE', NOW(), NOW()),
('Flyknit', 'ACTIVE', NOW(), NOW()),
('Textile', 'ACTIVE', NOW(), NOW()),
('EVA', 'ACTIVE', NOW(), NOW());

-- ============================================
-- 6. SIZES (15+ rows)
-- ============================================
INSERT INTO sizes (value, status, created_at, updated_at) VALUES
(35, 'ACTIVE', NOW(), NOW()),
(36, 'ACTIVE', NOW(), NOW()),
(37, 'ACTIVE', NOW(), NOW()),
(38, 'ACTIVE', NOW(), NOW()),
(39, 'ACTIVE', NOW(), NOW()),
(40, 'ACTIVE', NOW(), NOW()),
(41, 'ACTIVE', NOW(), NOW()),
(42, 'ACTIVE', NOW(), NOW()),
(43, 'ACTIVE', NOW(), NOW()),
(44, 'ACTIVE', NOW(), NOW()),
(45, 'ACTIVE', NOW(), NOW()),
(46, 'ACTIVE', NOW(), NOW()),
(47, 'ACTIVE', NOW(), NOW()),
(48, 'ACTIVE', NOW(), NOW()),
(49, 'ACTIVE', NOW(), NOW());

-- ============================================
-- 7. PRODUCTS (15+ rows)
-- ============================================
INSERT INTO products (code, name, brand_id, category_id, material_id, description, weight, status, created_at, updated_at) VALUES
('PRD001', 'Nike Air Max 90', 1, 4, 1, 'Classic running shoe with Air Max cushioning technology. Perfect for daily wear and light running activities.', 0.35, 'ACTIVE', NOW(), NOW()),
('PRD002', 'Adidas Ultraboost 22', 2, 1, 9, 'Premium running shoe with Boost midsole technology for maximum energy return and comfort.', 0.32, 'ACTIVE', NOW(), NOW()),
('PRD003', 'Nike Dunk Low', 1, 3, 2, 'Iconic basketball-inspired lifestyle shoe with classic design and versatile style.', 0.38, 'ACTIVE', NOW(), NOW()),
('PRD004', 'Adidas Stan Smith', 2, 3, 1, 'Timeless tennis shoe with clean minimalist design. Perfect for casual everyday wear.', 0.30, 'ACTIVE', NOW(), NOW()),
('PRD005', 'Puma Suede Classic', 3, 4, 7, 'Classic suede sneaker with retro style. Comfortable and stylish for streetwear.', 0.33, 'ACTIVE', NOW(), NOW()),
('PRD006', 'New Balance 550', 4, 4, 1, 'Retro basketball-inspired sneaker with premium leather upper and ABZORB cushioning.', 0.36, 'ACTIVE', NOW(), NOW()),
('PRD007', 'Converse Chuck Taylor All Star', 5, 3, 4, 'Iconic canvas sneaker with timeless design. Perfect for casual and street style.', 0.28, 'ACTIVE', NOW(), NOW()),
('PRD008', 'Vans Old Skool', 6, 9, 4, 'Classic skate shoe with signature side stripe. Durable and comfortable for skating and casual wear.', 0.31, 'ACTIVE', NOW(), NOW()),
('PRD009', 'Nike Air Force 1', 1, 2, 1, 'Legendary basketball shoe with Air-Sole unit. Iconic design loved by sneakerheads worldwide.', 0.40, 'ACTIVE', NOW(), NOW()),
('PRD010', 'Adidas Superstar', 2, 3, 1, 'Classic shell-toe sneaker with three-stripe design. Timeless style for everyday wear.', 0.34, 'ACTIVE', NOW(), NOW()),
('PRD011', 'Jordan 1 Retro High', 9, 2, 1, 'Iconic high-top basketball shoe with Air cushioning. Premium leather construction.', 0.42, 'ACTIVE', NOW(), NOW()),
('PRD012', 'Reebok Classic Leather', 7, 3, 1, 'Vintage-inspired sneaker with soft leather upper and comfortable fit.', 0.29, 'ACTIVE', NOW(), NOW()),
('PRD013', 'Nike Blazer Mid', 1, 4, 1, 'Retro basketball shoe with mid-top design. Versatile style for various occasions.', 0.37, 'ACTIVE', NOW(), NOW()),
('PRD014', 'Puma RS-X', 3, 4, 2, 'Futuristic running shoe with bold design and responsive cushioning technology.', 0.35, 'ACTIVE', NOW(), NOW()),
('PRD015', 'New Balance 574', 4, 1, 1, 'Classic running shoe with ENCAP midsole technology. Comfortable and durable.', 0.33, 'ACTIVE', NOW(), NOW());

-- ============================================
-- 8. PRODUCT_VARIANTS (20+ rows)
-- ============================================
INSERT INTO product_variants (product_id, color_id, size_id, price, stock, created_at, updated_at) VALUES
(1, 1, 7, 2500000.00, 50, NOW(), NOW()),
(1, 2, 7, 2500000.00, 45, NOW(), NOW()),
(1, 3, 7, 2500000.00, 30, NOW(), NOW()),
(2, 1, 6, 3200000.00, 40, NOW(), NOW()),
(2, 2, 6, 3200000.00, 35, NOW(), NOW()),
(2, 4, 6, 3200000.00, 25, NOW(), NOW()),
(3, 1, 8, 2800000.00, 60, NOW(), NOW()),
(3, 2, 8, 2800000.00, 55, NOW(), NOW()),
(3, 5, 8, 2800000.00, 40, NOW(), NOW()),
(4, 2, 7, 2200000.00, 70, NOW(), NOW()),
(4, 1, 7, 2200000.00, 65, NOW(), NOW()),
(5, 1, 7, 1800000.00, 50, NOW(), NOW()),
(5, 2, 7, 1800000.00, 45, NOW(), NOW()),
(6, 1, 8, 2400000.00, 40, NOW(), NOW()),
(6, 2, 8, 2400000.00, 35, NOW(), NOW()),
(7, 1, 7, 1500000.00, 80, NOW(), NOW()),
(7, 2, 7, 1500000.00, 75, NOW(), NOW()),
(8, 1, 7, 1600000.00, 60, NOW(), NOW()),
(8, 2, 7, 1600000.00, 55, NOW(), NOW()),
(9, 1, 8, 3000000.00, 50, NOW(), NOW()),
(9, 2, 8, 3000000.00, 45, NOW(), NOW()),
(10, 2, 7, 2100000.00, 55, NOW(), NOW()),
(10, 1, 7, 2100000.00, 50, NOW(), NOW()),
(11, 1, 9, 4500000.00, 30, NOW(), NOW()),
(11, 3, 9, 4500000.00, 25, NOW(), NOW());

-- ============================================
-- 9. PRODUCT_VARIANT_IMAGES (25+ rows)
-- ============================================
INSERT INTO product_variant_images (variant_id, image_url, created_at, updated_at) VALUES
(1, 'https://example.com/images/nike-airmax90-black-1.jpg', NOW(), NOW()),
(1, 'https://example.com/images/nike-airmax90-black-2.jpg', NOW(), NOW()),
(2, 'https://example.com/images/nike-airmax90-white-1.jpg', NOW(), NOW()),
(2, 'https://example.com/images/nike-airmax90-white-2.jpg', NOW(), NOW()),
(3, 'https://example.com/images/nike-airmax90-red-1.jpg', NOW(), NOW()),
(4, 'https://example.com/images/adidas-ultraboost-black-1.jpg', NOW(), NOW()),
(4, 'https://example.com/images/adidas-ultraboost-black-2.jpg', NOW(), NOW()),
(5, 'https://example.com/images/adidas-ultraboost-white-1.jpg', NOW(), NOW()),
(6, 'https://example.com/images/adidas-ultraboost-blue-1.jpg', NOW(), NOW()),
(7, 'https://example.com/images/nike-dunk-black-1.jpg', NOW(), NOW()),
(7, 'https://example.com/images/nike-dunk-black-2.jpg', NOW(), NOW()),
(8, 'https://example.com/images/nike-dunk-white-1.jpg', NOW(), NOW()),
(9, 'https://example.com/images/nike-dunk-green-1.jpg', NOW(), NOW()),
(10, 'https://example.com/images/adidas-stansmith-white-1.jpg', NOW(), NOW()),
(11, 'https://example.com/images/adidas-stansmith-black-1.jpg', NOW(), NOW()),
(12, 'https://example.com/images/puma-suede-black-1.jpg', NOW(), NOW()),
(13, 'https://example.com/images/puma-suede-white-1.jpg', NOW(), NOW()),
(14, 'https://example.com/images/nb550-black-1.jpg', NOW(), NOW()),
(15, 'https://example.com/images/nb550-white-1.jpg', NOW(), NOW()),
(16, 'https://example.com/images/converse-black-1.jpg', NOW(), NOW()),
(17, 'https://example.com/images/converse-white-1.jpg', NOW(), NOW()),
(18, 'https://example.com/images/vans-black-1.jpg', NOW(), NOW()),
(19, 'https://example.com/images/vans-white-1.jpg', NOW(), NOW()),
(20, 'https://example.com/images/nike-af1-black-1.jpg', NOW(), NOW()),
(21, 'https://example.com/images/nike-af1-white-1.jpg', NOW(), NOW());

-- ============================================
-- 10. VOUCHERS (15+ rows)
-- ============================================
INSERT INTO vouchers (code, name, type, value, quantity, used_count, start_date, end_date, min_order_value, max_discount, status, created_at, updated_at) VALUES
('VOUCHER10', 'Giảm 10%', 'PERCENTAGE', 10.00, 100, 5, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 500000.00, 200000.00, 'ACTIVE', NOW(), NOW()),
('VOUCHER20', 'Giảm 20%', 'PERCENTAGE', 20.00, 50, 2, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 1000000.00, 500000.00, 'ACTIVE', NOW(), NOW()),
('VOUCHER50K', 'Giảm 50.000đ', 'FIXED_AMOUNT', 50000.00, 200, 10, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 300000.00, NULL, 'ACTIVE', NOW(), NOW()),
('VOUCHER100K', 'Giảm 100.000đ', 'FIXED_AMOUNT', 100000.00, 100, 3, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 500000.00, NULL, 'ACTIVE', NOW(), NOW()),
('VOUCHER200K', 'Giảm 200.000đ', 'FIXED_AMOUNT', 200000.00, 50, 1, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 1000000.00, NULL, 'ACTIVE', NOW(), NOW()),
('NEWUSER', 'Giảm 15% cho khách hàng mới', 'PERCENTAGE', 15.00, 500, 25, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 200000.00, 150000.00, 'ACTIVE', NOW(), NOW()),
('SUMMER2024', 'Giảm 25% mùa hè', 'PERCENTAGE', 25.00, 30, 0, '2024-06-01 00:00:00', '2024-08-31 23:59:59', 800000.00, 400000.00, 'ACTIVE', NOW(), NOW()),
('VIP50K', 'Voucher VIP 50K', 'FIXED_AMOUNT', 50000.00, 100, 5, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 200000.00, NULL, 'ACTIVE', NOW(), NOW()),
('VIP100K', 'Voucher VIP 100K', 'FIXED_AMOUNT', 100000.00, 50, 2, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 500000.00, NULL, 'ACTIVE', NOW(), NOW()),
('WEEKEND10', 'Giảm 10% cuối tuần', 'PERCENTAGE', 10.00, 200, 15, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 300000.00, 100000.00, 'ACTIVE', NOW(), NOW()),
('BIRTHDAY20', 'Giảm 20% sinh nhật', 'PERCENTAGE', 20.00, 1000, 50, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 400000.00, 200000.00, 'ACTIVE', NOW(), NOW()),
('FLASH30', 'Flash Sale 30%', 'PERCENTAGE', 30.00, 20, 0, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 1500000.00, 600000.00, 'ACTIVE', NOW(), NOW()),
('FREESHIP', 'Miễn phí ship', 'FIXED_AMOUNT', 30000.00, 500, 30, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 200000.00, NULL, 'ACTIVE', NOW(), NOW()),
('MEGA50', 'Mega Sale 50%', 'PERCENTAGE', 50.00, 10, 0, '2024-11-01 00:00:00', '2024-11-30 23:59:59', 2000000.00, 1000000.00, 'ACTIVE', NOW(), NOW()),
('WELCOME15', 'Chào mừng 15%', 'PERCENTAGE', 15.00, 1000, 100, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 300000.00, 150000.00, 'ACTIVE', NOW(), NOW());

-- ============================================
-- 11. PROMOTIONS (15+ rows)
-- ============================================
INSERT INTO promotions (name, description, discount_percent, start_date, end_date, status, created_at, updated_at) VALUES
('Summer Sale 2024', 'Khuyến mãi mùa hè với giảm giá lớn cho tất cả sản phẩm', 20.00, '2024-06-01 00:00:00', '2024-08-31 23:59:59', 'ACTIVE', NOW(), NOW()),
('Back to School', 'Chương trình khuyến mãi cho học sinh, sinh viên', 15.00, '2024-08-01 00:00:00', '2024-09-30 23:59:59', 'ACTIVE', NOW(), NOW()),
('Black Friday', 'Siêu sale Black Friday với giảm giá lên đến 50%', 50.00, '2024-11-25 00:00:00', '2024-11-30 23:59:59', 'ACTIVE', NOW(), NOW()),
('New Year Sale', 'Khuyến mãi năm mới với nhiều ưu đãi hấp dẫn', 25.00, '2024-12-20 00:00:00', '2025-01-10 23:59:59', 'ACTIVE', NOW(), NOW()),
('Nike Collection Sale', 'Giảm giá đặc biệt cho bộ sưu tập Nike', 30.00, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 'ACTIVE', NOW(), NOW()),
('Adidas Flash Sale', 'Flash sale cho sản phẩm Adidas', 25.00, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 'ACTIVE', NOW(), NOW()),
('Weekend Special', 'Khuyến mãi cuối tuần cho tất cả sản phẩm', 10.00, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 'ACTIVE', NOW(), NOW()),
('Running Shoes Promotion', 'Giảm giá giày chạy bộ', 20.00, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 'ACTIVE', NOW(), NOW()),
('Basketball Shoes Sale', 'Khuyến mãi giày bóng rổ', 15.00, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 'ACTIVE', NOW(), NOW()),
('Lifestyle Collection', 'Giảm giá bộ sưu tập lifestyle', 12.00, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 'ACTIVE', NOW(), NOW()),
('Mid-Year Sale', 'Khuyến mãi giữa năm', 18.00, '2024-06-15 00:00:00', '2024-07-15 23:59:59', 'ACTIVE', NOW(), NOW()),
('End of Season', 'Giảm giá cuối mùa', 35.00, '2024-02-01 00:00:00', '2024-02-29 23:59:59', 'ACTIVE', NOW(), NOW()),
('Spring Collection', 'Khuyến mãi bộ sưu tập mùa xuân', 22.00, '2024-03-01 00:00:00', '2024-05-31 23:59:59', 'ACTIVE', NOW(), NOW()),
('Winter Warm Up', 'Khuyến mãi mùa đông', 20.00, '2024-12-01 00:00:00', '2024-12-31 23:59:59', 'ACTIVE', NOW(), NOW()),
('Mega Clearance', 'Thanh lý siêu lớn', 40.00, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 'ACTIVE', NOW(), NOW());

-- ============================================
-- 12. PROMOTION_PRODUCTS (Junction Table - 20+ rows)
-- ============================================
INSERT INTO promotion_products (promotion_id, product_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
(2, 6), (2, 7), (2, 8),
(3, 1), (3, 2), (3, 9), (3, 10),
(4, 11), (4, 12), (4, 13),
(5, 1), (5, 3), (5, 9), (5, 13),
(6, 2), (6, 4), (6, 10),
(7, 1), (7, 2), (7, 3), (7, 4), (7, 5);

-- ============================================
-- 13. ACCOUNT_ADDRESSES (15+ rows)
-- ============================================
INSERT INTO account_addresses (account_id, name, phone_number, province_id, district_id, ward_id, specific_address, type, is_default, created_at, updated_at) VALUES
(2, 'Nguyen Van A', '0901234568', '79', '760', '26734', '123 Đường ABC, Phường XYZ', 0, 1, NOW(), NOW()),
(2, 'Nguyen Van A', '0901234568', '79', '760', '26735', '456 Đường DEF, Phường UVW', 0, 0, NOW(), NOW()),
(4, 'Nguyen Van A', '0901111111', '79', '760', '26734', '789 Đường GHI, Phường RST', 0, 1, NOW(), NOW()),
(5, 'Tran Thi B', '0902222222', '01', '001', '00001', '321 Đường JKL, Phường MNO', 0, 1, NOW(), NOW()),
(6, 'Le Van C', '0903333333', '48', '490', '20215', '654 Đường PQR, Phường STU', 0, 1, NOW(), NOW()),
(7, 'Pham Thi D', '0904444444', '79', '760', '26734', '987 Đường VWX, Phường YZA', 0, 1, NOW(), NOW()),
(8, 'Hoang Van E', '0905555555', '24', '213', '07198', '147 Đường BCD, Phường EFG', 0, 1, NOW(), NOW()),
(9, 'Vu Thi F', '0906666666', '79', '760', '26735', '258 Đường HIJ, Phường KLM', 0, 1, NOW(), NOW()),
(10, 'Dang Van G', '0907777777', '31', '312', '11689', '369 Đường NOP, Phường QRS', 0, 1, NOW(), NOW()),
(11, 'Bui Thi H', '0908888888', '79', '760', '26734', '741 Đường TUV, Phường WXY', 0, 1, NOW(), NOW()),
(12, 'Do Van I', '0909999999', '92', '916', '31156', '852 Đường ZAB, Phường CDE', 0, 1, NOW(), NOW()),
(13, 'Ngo Thi K', '0910000000', '79', '760', '26735', '963 Đường FGH, Phường IJK', 0, 1, NOW(), NOW()),
(4, 'Nguyen Van A', '0901111111', '01', '001', '00001', '159 Đường LMN, Phường OPQ', 0, 0, NOW(), NOW()),
(5, 'Tran Thi B', '0902222222', '48', '490', '20215', '357 Đường RST, Phường UVW', 0, 0, NOW(), NOW()),
(6, 'Le Van C', '0903333333', '24', '213', '07198', '468 Đường XYZ, Phường ABC', 0, 0, NOW(), NOW());

-- ============================================
-- 14. ORDERS (15+ rows)
-- ============================================
INSERT INTO orders (code, customer_id, staff_id, voucher_id, sub_total, discount, total, shipping_name, shipping_phone_number, shipping_province_id, shipping_district_id, shipping_ward_id, shipping_specific_address, payment_method, payment_status, order_status, created_at, updated_at) VALUES
('ORD001', 2, 3, 1, 2500000.00, 250000.00, 2250000.00, 'Nguyen Van A', '0901234568', '79', '760', '26734', '123 Đường ABC, Phường XYZ', 'COD', 'PENDING', 'CHO_XAC_NHAN', NOW(), NOW()),
('ORD002', 4, NULL, NULL, 3200000.00, 0.00, 3200000.00, 'Nguyen Van A', '0901111111', '79', '760', '26734', '789 Đường GHI, Phường RST', 'BANK_TRANSFER', 'PENDING', 'CHO_GIAO_HANG', NOW(), NOW()),
('ORD003', 5, 3, 3, 1800000.00, 50000.00, 1750000.00, 'Tran Thi B', '0902222222', '01', '001', '00001', '321 Đường JKL, Phường MNO', 'COD', 'PENDING', 'DANG_VAN_CHUYEN', NOW(), NOW()),
('ORD004', 6, NULL, NULL, 2200000.00, 0.00, 2200000.00, 'Le Van C', '0903333333', '48', '490', '20215', '654 Đường PQR, Phường STU', 'CASH', 'PAID', 'DA_GIAO_HANG', NOW(), NOW()),
('ORD005', 7, 14, 2, 5000000.00, 1000000.00, 4000000.00, 'Pham Thi D', '0904444444', '79', '760', '26734', '987 Đường VWX, Phường YZA', 'BANK_TRANSFER', 'PAID', 'HOAN_THANH', NOW(), NOW()),
('ORD006', 8, NULL, NULL, 1500000.00, 0.00, 1500000.00, 'Hoang Van E', '0905555555', '24', '213', '07198', '147 Đường BCD, Phường EFG', 'COD', 'PENDING', 'CHO_XAC_NHAN', NOW(), NOW()),
('ORD007', 9, 3, 1, 2800000.00, 280000.00, 2520000.00, 'Vu Thi F', '0906666666', '79', '760', '26735', '258 Đường HIJ, Phường KLM', 'CASH', 'PAID', 'DA_GIAO_HANG', NOW(), NOW()),
('ORD008', 10, NULL, NULL, 2400000.00, 0.00, 2400000.00, 'Dang Van G', '0907777777', '31', '312', '11689', '369 Đường NOP, Phường QRS', 'BANK_TRANSFER', 'PARTIAL_PAID', 'DANG_VAN_CHUYEN', NOW(), NOW()),
('ORD009', 11, 14, 4, 4500000.00, 100000.00, 4400000.00, 'Bui Thi H', '0908888888', '79', '760', '26734', '741 Đường TUV, Phường WXY', 'CASH', 'PAID', 'HOAN_THANH', NOW(), NOW()),
('ORD010', 12, NULL, NULL, 1600000.00, 0.00, 1600000.00, 'Do Van I', '0909999999', '92', '916', '31156', '852 Đường ZAB, Phường CDE', 'COD', 'PENDING', 'CHO_XAC_NHAN', NOW(), NOW()),
('ORD011', 13, 3, NULL, 2100000.00, 0.00, 2100000.00, 'Ngo Thi K', '0910000000', '79', '760', '26735', '963 Đường FGH, Phường IJK', 'BANK_TRANSFER', 'PAID', 'DA_GIAO_HANG', NOW(), NOW()),
('ORD012', 2, NULL, 5, 3000000.00, 200000.00, 2800000.00, 'Nguyen Van A', '0901234568', '79', '760', '26734', '123 Đường ABC, Phường XYZ', 'COD', 'PENDING', 'CHO_GIAO_HANG', NOW(), NOW()),
('ORD013', 4, 14, NULL, 1800000.00, 0.00, 1800000.00, 'Nguyen Van A', '0901111111', '79', '760', '26734', '789 Đường GHI, Phường RST', 'CASH', 'PAID', 'HOAN_THANH', NOW(), NOW()),
('ORD014', 5, NULL, 1, 2500000.00, 250000.00, 2250000.00, 'Tran Thi B', '0902222222', '01', '001', '00001', '321 Đường JKL, Phường MNO', 'BANK_TRANSFER', 'PARTIAL_PAID', 'DANG_VAN_CHUYEN', NOW(), NOW()),
('ORD015', 6, 3, NULL, 3200000.00, 0.00, 3200000.00, 'Le Van C', '0903333333', '48', '490', '20215', '654 Đường PQR, Phường STU', 'COD', 'PENDING', 'CHO_XAC_NHAN', NOW(), NOW());

-- ============================================
-- 15. ORDER_ITEMS (20+ rows)
-- ============================================
INSERT INTO order_items (order_id, variant_id, quantity, price, created_at, updated_at) VALUES
(1, 1, 1, 2500000.00, NOW(), NOW()),
(2, 4, 1, 3200000.00, NOW(), NOW()),
(3, 12, 1, 1800000.00, NOW(), NOW()),
(4, 10, 1, 2200000.00, NOW(), NOW()),
(5, 20, 1, 3000000.00, NOW(), NOW()),
(5, 21, 1, 3000000.00, NOW(), NOW()),
(6, 16, 1, 1500000.00, NOW(), NOW()),
(7, 7, 1, 2800000.00, NOW(), NOW()),
(8, 14, 1, 2400000.00, NOW(), NOW()),
(9, 23, 1, 4500000.00, NOW(), NOW()),
(10, 18, 1, 1600000.00, NOW(), NOW()),
(11, 22, 1, 2100000.00, NOW(), NOW()),
(12, 20, 1, 3000000.00, NOW(), NOW()),
(13, 12, 1, 1800000.00, NOW(), NOW()),
(14, 1, 1, 2500000.00, NOW(), NOW()),
(15, 4, 1, 3200000.00, NOW(), NOW()),
(1, 2, 1, 2500000.00, NOW(), NOW()),
(2, 5, 1, 3200000.00, NOW(), NOW()),
(3, 13, 1, 1800000.00, NOW(), NOW()),
(4, 11, 1, 2200000.00, NOW(), NOW()),
(7, 8, 1, 2800000.00, NOW(), NOW()),
(8, 15, 1, 2400000.00, NOW(), NOW());

-- ============================================
-- 16. PAYMENTS (20+ rows)
-- ============================================
INSERT INTO payments (order_id, amount, method, status, bank_transfer_info, note, created_at, updated_at) VALUES
(1, 2250000.00, 'COD', 'PENDING', NULL, 'Thanh toán khi nhận hàng', NOW(), NOW()),
(2, 3200000.00, 'BANK_TRANSFER', 'PENDING', '{"bank": "Vietcombank", "account": "1234567890", "transaction": "TXN001"}', 'Chuyển khoản ngân hàng', NOW(), NOW()),
(3, 1750000.00, 'COD', 'PENDING', NULL, 'Thanh toán khi nhận hàng', NOW(), NOW()),
(4, 2200000.00, 'CASH', 'COMPLETED', NULL, 'Thanh toán tiền mặt tại cửa hàng', NOW(), NOW()),
(5, 4000000.00, 'BANK_TRANSFER', 'COMPLETED', '{"bank": "BIDV", "account": "0987654321", "transaction": "TXN002"}', 'Đã thanh toán đầy đủ', NOW(), NOW()),
(6, 1500000.00, 'COD', 'PENDING', NULL, 'Thanh toán khi nhận hàng', NOW(), NOW()),
(7, 2520000.00, 'CASH', 'COMPLETED', NULL, 'Thanh toán tiền mặt', NOW(), NOW()),
(8, 1200000.00, 'BANK_TRANSFER', 'COMPLETED', '{"bank": "Techcombank", "account": "1122334455", "transaction": "TXN003"}', 'Thanh toán một phần', NOW(), NOW()),
(8, 1200000.00, 'BANK_TRANSFER', 'PENDING', '{"bank": "Techcombank", "account": "1122334455", "transaction": "TXN004"}', 'Thanh toán phần còn lại', NOW(), NOW()),
(9, 4400000.00, 'CASH', 'COMPLETED', NULL, 'Thanh toán tiền mặt đầy đủ', NOW(), NOW()),
(10, 1600000.00, 'COD', 'PENDING', NULL, 'Thanh toán khi nhận hàng', NOW(), NOW()),
(11, 2100000.00, 'BANK_TRANSFER', 'COMPLETED', '{"bank": "Vietinbank", "account": "5566778899", "transaction": "TXN005"}', 'Đã thanh toán', NOW(), NOW()),
(12, 2800000.00, 'COD', 'PENDING', NULL, 'Thanh toán khi nhận hàng', NOW(), NOW()),
(13, 1800000.00, 'CASH', 'COMPLETED', NULL, 'Thanh toán tiền mặt', NOW(), NOW()),
(14, 1125000.00, 'BANK_TRANSFER', 'COMPLETED', '{"bank": "ACB", "account": "9988776655", "transaction": "TXN006"}', 'Thanh toán một phần', NOW(), NOW()),
(14, 1125000.00, 'BANK_TRANSFER', 'PENDING', '{"bank": "ACB", "account": "9988776655", "transaction": "TXN007"}', 'Thanh toán phần còn lại', NOW(), NOW()),
(15, 3200000.00, 'COD', 'PENDING', NULL, 'Thanh toán khi nhận hàng', NOW(), NOW()),
(2, 3200000.00, 'VNPAY', 'COMPLETED', '{"transaction": "VNPAY001", "payment_id": "12345"}', 'Thanh toán qua VNPay', NOW(), NOW()),
(5, 4000000.00, 'MOMO', 'COMPLETED', '{"transaction": "MOMO001", "payment_id": "67890"}', 'Thanh toán qua MoMo', NOW(), NOW()),
(11, 2100000.00, 'VNPAY', 'COMPLETED', '{"transaction": "VNPAY002", "payment_id": "11111"}', 'Thanh toán qua VNPay', NOW(), NOW());

-- ============================================
-- 17. RETURNS (12+ rows)
-- ============================================
INSERT INTO returns (code, original_order_id, customer_id, staff_id, items, total_refund, reason, note, status, created_at, updated_at) VALUES
('RET001', 4, 6, 3, '[{"variantId": 10, "quantity": 1, "price": 2200000.00}]', 2200000.00, 'Sản phẩm không đúng kích thước', 'Khách hàng yêu cầu đổi size', 'CHO_XU_LY', NOW(), NOW()),
('RET002', 7, 9, 14, '[{"variantId": 7, "quantity": 1, "price": 2800000.00}]', 2800000.00, 'Sản phẩm bị lỗi', 'Giày bị trầy xước', 'DA_HOAN_TIEN', NOW(), NOW()),
('RET003', 9, 11, NULL, '[{"variantId": 23, "quantity": 1, "price": 4500000.00}]', 4500000.00, 'Không vừa', 'Size không phù hợp', 'CHO_XU_LY', NOW(), NOW()),
('RET004', 11, 13, 3, '[{"variantId": 22, "quantity": 1, "price": 2100000.00}]', 2100000.00, 'Sản phẩm không đúng mô tả', 'Màu sắc không đúng', 'DA_HOAN_TIEN', NOW(), NOW()),
('RET005', 13, 4, 14, '[{"variantId": 12, "quantity": 1, "price": 1800000.00}]', 1800000.00, 'Không hài lòng', 'Chất lượng không như mong đợi', 'CHO_XU_LY', NOW(), NOW()),
('RET006', 5, 7, NULL, '[{"variantId": 20, "quantity": 1, "price": 3000000.00}]', 3000000.00, 'Đổi ý', 'Không muốn mua nữa', 'DA_HUY', NOW(), NOW()),
('RET007', 1, 2, 3, '[{"variantId": 1, "quantity": 1, "price": 2500000.00}]', 2250000.00, 'Sản phẩm bị hỏng', 'Giày bị nứt đế', 'DA_HOAN_TIEN', NOW(), NOW()),
('RET008', 8, 10, 14, '[{"variantId": 14, "quantity": 1, "price": 2400000.00}]', 2400000.00, 'Không vừa chân', 'Size quá nhỏ', 'CHO_XU_LY', NOW(), NOW()),
('RET009', 12, 2, NULL, '[{"variantId": 20, "quantity": 1, "price": 3000000.00}]', 2800000.00, 'Sản phẩm lỗi', 'Có vết bẩn trên giày', 'CHO_XU_LY', NOW(), NOW()),
('RET010', 4, 6, 3, '[{"variantId": 10, "quantity": 1, "price": 2200000.00}]', 2200000.00, 'Đổi màu', 'Muốn đổi sang màu khác', 'DA_HOAN_TIEN', NOW(), NOW()),
('RET011', 7, 9, 14, '[{"variantId": 8, "quantity": 1, "price": 2800000.00}]', 2800000.00, 'Không phù hợp', 'Kiểu dáng không hợp', 'CHO_XU_LY', NOW(), NOW()),
('RET012', 11, 13, 3, '[{"variantId": 22, "quantity": 1, "price": 2100000.00}]', 2100000.00, 'Sản phẩm lỗi', 'Đường chỉ bị lỗi', 'DA_HOAN_TIEN', NOW(), NOW());

-- ============================================
-- 18. NOTIFICATIONS (20+ rows)
-- ============================================
INSERT INTO notifications (account_id, title, message, type, is_read, created_at, updated_at) VALUES
(2, 'Đơn hàng đã được xác nhận', 'Đơn hàng ORD001 của bạn đã được xác nhận và đang được chuẩn bị', 'ORDER', 0, NOW(), NOW()),
(2, 'Đơn hàng đang được vận chuyển', 'Đơn hàng ORD012 của bạn đang được vận chuyển', 'ORDER', 0, NOW(), NOW()),
(4, 'Đơn hàng đã được giao', 'Đơn hàng ORD002 của bạn đã được giao thành công', 'ORDER', 1, NOW(), NOW()),
(4, 'Đơn hàng hoàn thành', 'Đơn hàng ORD013 của bạn đã hoàn thành', 'ORDER', 1, NOW(), NOW()),
(5, 'Đơn hàng đang vận chuyển', 'Đơn hàng ORD003 của bạn đang được vận chuyển', 'ORDER', 0, NOW(), NOW()),
(5, 'Đơn hàng đang vận chuyển', 'Đơn hàng ORD014 của bạn đang được vận chuyển', 'ORDER', 0, NOW(), NOW()),
(6, 'Đơn hàng đã được giao', 'Đơn hàng ORD004 của bạn đã được giao thành công', 'ORDER', 1, NOW(), NOW()),
(6, 'Đơn hàng đã được xác nhận', 'Đơn hàng ORD015 của bạn đã được xác nhận', 'ORDER', 0, NOW(), NOW()),
(7, 'Đơn hàng hoàn thành', 'Đơn hàng ORD005 của bạn đã hoàn thành', 'ORDER', 1, NOW(), NOW()),
(8, 'Đơn hàng đã được xác nhận', 'Đơn hàng ORD008 của bạn đã được xác nhận', 'ORDER', 0, NOW(), NOW()),
(9, 'Đơn hàng đã được giao', 'Đơn hàng ORD007 của bạn đã được giao thành công', 'ORDER', 1, NOW(), NOW()),
(11, 'Đơn hàng hoàn thành', 'Đơn hàng ORD009 của bạn đã hoàn thành', 'ORDER', 1, NOW(), NOW()),
(13, 'Đơn hàng đã được giao', 'Đơn hàng ORD011 của bạn đã được giao thành công', 'ORDER', 1, NOW(), NOW()),
(NULL, 'Khuyến mãi mới', 'Khuyến mãi Summer Sale 2024 với giảm giá lên đến 20%', 'PROMOTION', 0, NOW(), NOW()),
(NULL, 'Voucher mới', 'Bạn có voucher mới: VOUCHER10 - Giảm 10% cho đơn hàng từ 500.000đ', 'VOUCHER', 0, NOW(), NOW()),
(NULL, 'Thông báo hệ thống', 'Hệ thống sẽ bảo trì vào ngày 15/12/2024 từ 2h-4h sáng', 'SYSTEM', 0, NOW(), NOW()),
(2, 'Voucher mới', 'Bạn có voucher mới: NEWUSER - Giảm 15% cho khách hàng mới', 'VOUCHER', 0, NOW(), NOW()),
(4, 'Khuyến mãi', 'Khuyến mãi Back to School với giảm giá 15%', 'PROMOTION', 0, NOW(), NOW()),
(5, 'Voucher', 'Bạn có voucher: VOUCHER50K - Giảm 50.000đ', 'VOUCHER', 0, NOW(), NOW()),
(6, 'Thông báo hệ thống', 'Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi', 'SYSTEM', 1, NOW(), NOW());

SET FOREIGN_KEY_CHECKS = 1;

-- ============================================
-- Script completed successfully!
-- ============================================

