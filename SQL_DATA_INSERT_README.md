# Hướng dẫn Insert Data vào MySQL

## Tổng quan
File `insert_data.sql` chứa script SQL để insert dữ liệu mẫu vào database `sneakerDB`. Mỗi bảng có trên 10 rows dữ liệu.

## Các tài khoản mặc định

### Admin
- **Email**: admin123@gmail.com
- **Password**: Admin123!
- **Role**: ADMIN

### Customer
- **Email**: customer123@gmail.com
- **Password**: Customer123!
- **Role**: CUSTOMER

### Staff
- **Email**: staff123@gmail.com
- **Password**: Staff123!
- **Role**: STAFF

## Cách sử dụng

### 1. Tạo BCrypt Hash cho mật khẩu

Mật khẩu trong database được hash bằng BCrypt. Để generate hash mới:

**Cách 1: Sử dụng Java Utility**
```bash
# Compile và chạy utility
mvn compile exec:java -Dexec.mainClass="com.sneaker.util.PasswordHashGenerator"
```

**Cách 2: Sử dụng Spring Boot Test**
Tạo một test class hoặc sử dụng Spring Boot shell để generate hash.

**Cách 3: Online BCrypt Generator**
Sử dụng các tool online như: https://bcrypt-generator.com/

### 2. Cập nhật mật khẩu trong SQL (nếu cần)

Nếu bạn muốn thay đổi mật khẩu, hãy:
1. Generate BCrypt hash cho mật khẩu mới
2. Thay thế hash trong file `insert_data.sql` tại phần INSERT accounts

### 3. Chạy Script SQL

```sql
-- Kết nối MySQL
mysql -u root -p sneakerDB

-- Hoặc sử dụng MySQL Workbench, phpMyAdmin, etc.
-- Chạy file insert_data.sql
source insert_data.sql;
```

Hoặc sử dụng command line:
```bash
mysql -u root -p sneakerDB < insert_data.sql
```

## Cấu trúc dữ liệu

Script insert dữ liệu cho các bảng sau (mỗi bảng có trên 10 rows):

1. **accounts** (15 rows) - Tài khoản người dùng
2. **brands** (12 rows) - Thương hiệu giày
3. **categories** (12 rows) - Danh mục sản phẩm
4. **colors** (15 rows) - Màu sắc
5. **materials** (12 rows) - Chất liệu
6. **sizes** (15 rows) - Kích thước
7. **products** (15 rows) - Sản phẩm
8. **product_variants** (25 rows) - Biến thể sản phẩm
9. **product_variant_images** (25 rows) - Hình ảnh sản phẩm
10. **vouchers** (15 rows) - Mã giảm giá
11. **promotions** (15 rows) - Khuyến mãi
12. **promotion_products** (20 rows) - Sản phẩm khuyến mãi (junction table)
13. **account_addresses** (15 rows) - Địa chỉ người dùng
14. **orders** (15 rows) - Đơn hàng
15. **order_items** (22 rows) - Chi tiết đơn hàng
16. **payments** (20 rows) - Thanh toán
17. **returns** (12 rows) - Đổi trả
18. **notifications** (20 rows) - Thông báo

## Lưu ý

1. **Foreign Key Constraints**: Script tự động tắt kiểm tra foreign key (`SET FOREIGN_KEY_CHECKS = 0`) ở đầu và bật lại ở cuối.

2. **Timestamps**: Tất cả `created_at` và `updated_at` được set bằng `NOW()`.

3. **Password Security**: 
   - Mật khẩu được hash bằng BCrypt với cost factor 10
   - Format: `$2a$10$...`
   - Mỗi lần hash sẽ tạo ra kết quả khác nhau (do salt ngẫu nhiên)

4. **Dữ liệu mẫu**: 
   - Dữ liệu trong script là dữ liệu mẫu để test
   - Có thể cần điều chỉnh theo nhu cầu thực tế

5. **Image URLs**: 
   - URLs hình ảnh trong script là ví dụ (`https://example.com/...`)
   - Cần thay thế bằng URLs thực tế hoặc upload images lên server

## Troubleshooting

### Lỗi Foreign Key Constraint
- Đảm bảo script chạy đúng thứ tự (từ bảng cha đến bảng con)
- Kiểm tra xem các ID tham chiếu có tồn tại không

### Lỗi Duplicate Entry
- Kiểm tra các trường UNIQUE (code, email, etc.)
- Xóa dữ liệu cũ trước khi insert mới (nếu cần)

### Password không đúng
- Verify BCrypt hash bằng cách test login
- Regenerate hash nếu cần thiết

## Kiểm tra dữ liệu sau khi insert

```sql
-- Kiểm tra số lượng records
SELECT 'accounts' as table_name, COUNT(*) as count FROM accounts
UNION ALL
SELECT 'products', COUNT(*) FROM products
UNION ALL
SELECT 'orders', COUNT(*) FROM orders
-- ... etc
;

-- Kiểm tra tài khoản
SELECT id, code, email, role, status FROM accounts WHERE email IN (
    'admin123@gmail.com',
    'customer123@gmail.com', 
    'staff123@gmail.com'
);
```

