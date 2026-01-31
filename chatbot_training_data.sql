-- Training Data cho Chatbot - Cửa hàng Giày Sneaker
-- Insert vào bảng chatbot_trainings

-- ========== SIZE GUIDE ==========
INSERT INTO chatbot_trainings (question, answer, category, priority, status, created_at, updated_at) VALUES
('Làm thế nào để chọn size giày phù hợp?', 
'Để chọn size giày phù hợp, bạn nên:
1. Đo chiều dài bàn chân từ gót đến ngón dài nhất (đo vào buổi chiều vì bàn chân sẽ phồng lên)
2. Tham khảo bảng size của chúng tôi
3. Nếu bàn chân bạn rộng, nên chọn size lớn hơn 0.5
4. Nếu mua giày chạy bộ, nên chọn size lớn hơn 0.5-1 size so với size thường ngày
5. Thử giày vào buổi chiều để đảm bảo vừa vặn nhất

Bạn có thể cho tôi biết chiều dài bàn chân của bạn (tính bằng cm) để tôi tư vấn size chính xác hơn không?', 
'SIZE_GUIDE', 10, 'ACTIVE', NOW(), NOW()),

('Bảng size giày như thế nào?', 
'Bảng size giày của chúng tôi:
- Size 38: 24.0 - 24.5 cm
- Size 39: 24.5 - 25.0 cm
- Size 40: 25.0 - 25.5 cm
- Size 41: 25.5 - 26.0 cm
- Size 42: 26.0 - 26.5 cm
- Size 43: 26.5 - 27.0 cm
- Size 44: 27.0 - 27.5 cm
- Size 45: 27.5 - 28.0 cm

Lưu ý: Mỗi hãng có thể có size khác nhau một chút. Nike thường nhỏ hơn 0.5 size so với Adidas.', 
'SIZE_GUIDE', 9, 'ACTIVE', NOW(), NOW()),

('Tôi mang size 42 có vừa không?', 
'Size 42 phù hợp với bàn chân có chiều dài từ 26.0 - 26.5 cm. 
Để chắc chắn hơn, bạn có thể:
1. Đo chính xác chiều dài bàn chân
2. Tham khảo review của khách hàng khác về sản phẩm
3. Đến cửa hàng để thử trực tiếp
4. Mua online với chính sách đổi trả miễn phí trong 7 ngày

Bạn muốn tôi giới thiệu các mẫu giày size 42 đang có sẵn không?', 
'SIZE_GUIDE', 8, 'ACTIVE', NOW(), NOW());

-- ========== PRODUCT INFO ==========
INSERT INTO chatbot_trainings (question, answer, category, priority, status, created_at, updated_at) VALUES
('Có những loại giày gì?', 
'Chúng tôi có các loại giày sau:
1. **Giày chạy bộ (Running)**: Nike Air Zoom, Adidas Ultraboost, Asics Gel-Kayano
2. **Giày thể thao (Training)**: Nike Metcon, Reebok Nano, Adidas Alphabounce
3. **Giày bóng rổ (Basketball)**: Nike LeBron, Air Jordan, Adidas Harden
4. **Giày lifestyle**: Nike Air Force 1, Adidas Stan Smith, Vans Old Skool
5. **Giày đá bóng**: Nike Mercurial, Adidas Predator, Puma Future

Bạn quan tâm đến loại giày nào?', 
'PRODUCT_INFO', 10, 'ACTIVE', NOW(), NOW()),

('Giày Nike có những dòng nào?', 
'Các dòng giày Nike phổ biến:
1. **Nike Air Max**: Thiết kế iconic với đệm khí nhìn thấy được
2. **Nike Air Force 1**: Classic lifestyle, phù hợp mọi outfit
3. **Nike Air Jordan**: Dòng bóng rổ huyền thoại
4. **Nike Pegasus**: Giày chạy bộ đa năng
5. **Nike React**: Công nghệ đệm mới nhất, êm ái
6. **Nike Blazer**: Retro style, phong cách vintage

Giá từ 1.500.000đ - 5.000.000đ tùy model. Bạn muốn xem chi tiết dòng nào?', 
'PRODUCT_INFO', 9, 'ACTIVE', NOW(), NOW()),

('Giày Adidas có gì hot?', 
'Các dòng Adidas đang hot:
1. **Adidas Ultraboost**: Đệm Boost siêu êm, giá 3.500.000đ - 4.500.000đ
2. **Adidas NMD**: Phong cách streetwear, giá 2.500.000đ - 3.500.000đ
3. **Adidas Stan Smith**: Classic trắng xanh, giá 1.800.000đ - 2.200.000đ
4. **Adidas Yeezy**: Limited edition, giá 5.000.000đ+
5. **Adidas Superstar**: Shell toe iconic, giá 1.900.000đ - 2.500.000đ

Tất cả đều có sẵn nhiều size và màu. Bạn thích dòng nào?', 
'PRODUCT_INFO', 9, 'ACTIVE', NOW(), NOW()),

('Giày chạy bộ nào tốt?', 
'Top giày chạy bộ được đánh giá cao:

**Phân khúc 2-3 triệu:**
- Nike Pegasus 40: 2.850.000đ - Đa năng, bền
- Adidas Solarboost: 2.650.000đ - Đệm tốt
- Asics Gel-Cumulus: 2.450.000đ - Ổn định

**Phân khúc 3-4 triệu:**
- Nike ZoomX Invincible: 3.950.000đ - Đệm cực êm
- Adidas Ultraboost 22: 3.750.000đ - Boost technology
- Asics Gel-Nimbus: 3.650.000đ - Cho người chân nặng

Bạn chạy bao nhiêu km/tuần và ngân sách bao nhiêu để tôi tư vấn chính xác hơn?', 
'PRODUCT_INFO', 10, 'ACTIVE', NOW(), NOW());

-- ========== ORDER GUIDE ==========
INSERT INTO chatbot_trainings (question, answer, category, priority, status, created_at, updated_at) VALUES
('Làm thế nào để đặt hàng?', 
'Quy trình đặt hàng rất đơn giản:

**Online:**
1. Chọn sản phẩm → Thêm vào giỏ hàng
2. Điền thông tin giao hàng
3. Chọn phương thức thanh toán (COD/Chuyển khoản/Thẻ)
4. Xác nhận đơn hàng
5. Nhận mã đơn hàng qua email/SMS

**Tại cửa hàng:**
1. Đến cửa hàng gần nhất
2. Thử giày và chọn size
3. Thanh toán tại quầy
4. Nhận hàng ngay

Bạn muốn đặt hàng online hay đến cửa hàng?', 
'ORDER_GUIDE', 10, 'ACTIVE', NOW(), NOW()),

('Có những hình thức thanh toán nào?', 
'Chúng tôi hỗ trợ các hình thức thanh toán:

1. **COD (Thanh toán khi nhận hàng)**
   - Phí ship: 30.000đ
   - Miễn phí ship đơn từ 500.000đ

2. **Chuyển khoản ngân hàng**
   - Giảm 2% tổng đơn
   - Miễn phí ship

3. **Thẻ tín dụng/Thẻ ATM**
   - Visa, Mastercard, JCB
   - Miễn phí ship

4. **Ví điện tử**
   - MoMo, ZaloPay, VNPay
   - Giảm 1% tổng đơn

Bạn muốn thanh toán bằng hình thức nào?', 
'ORDER_GUIDE', 9, 'ACTIVE', NOW(), NOW()),

('Bao lâu thì nhận được hàng?', 
'Thời gian giao hàng:

**Nội thành Hà Nội/HCM:**
- Giao trong 2-4 giờ (nếu đặt trước 15h)
- Giao trong ngày hôm sau (nếu đặt sau 15h)

**Các tỉnh thành khác:**
- Miền Bắc: 1-2 ngày
- Miền Trung: 2-3 ngày
- Miền Nam: 2-3 ngày

**Vùng xa:**
- 3-5 ngày

Bạn có thể track đơn hàng real-time qua app hoặc website. Bạn ở khu vực nào để tôi tư vấn chính xác hơn?', 
'ORDER_GUIDE', 9, 'ACTIVE', NOW(), NOW());

-- ========== RETURN POLICY ==========
INSERT INTO chatbot_trainings (question, answer, category, priority, status, created_at, updated_at) VALUES
('Chính sách đổi trả như thế nào?', 
'Chính sách đổi trả của chúng tôi:

**Thời gian:**
- Đổi/trả trong vòng 7 ngày kể từ ngày nhận hàng
- Đổi size miễn phí trong 30 ngày

**Điều kiện:**
- Sản phẩm chưa qua sử dụng
- Còn nguyên tem mác, hộp
- Có hóa đơn mua hàng

**Phí đổi trả:**
- Miễn phí nếu lỗi từ nhà sản xuất
- Miễn phí đổi size (1 lần)
- Phí ship 2 chiều nếu đổi ý (60.000đ)

**Quy trình:**
1. Liên hệ hotline: 1900-xxxx
2. Gửi hàng về (hoặc đến cửa hàng)
3. Kiểm tra sản phẩm
4. Đổi mới hoặc hoàn tiền trong 3-5 ngày

Bạn muốn đổi hay trả sản phẩm?', 
'RETURN_POLICY', 10, 'ACTIVE', NOW(), NOW()),

('Tôi muốn đổi size có được không?', 
'Hoàn toàn được! Chính sách đổi size:

**Miễn phí đổi size:**
- Trong vòng 30 ngày
- Đổi được 1 lần
- Sản phẩm chưa sử dụng

**Cách đổi:**
1. Liên hệ hotline hoặc đến cửa hàng
2. Mang theo hóa đơn và sản phẩm
3. Chọn size mới
4. Đổi ngay (nếu có hàng)

**Lưu ý:**
- Nếu size mới hết hàng, chúng tôi sẽ đặt trước cho bạn
- Có thể đổi sang model khác cùng giá
- Không áp dụng cho sản phẩm sale

Bạn muốn đổi size nào?', 
'RETURN_POLICY', 9, 'ACTIVE', NOW(), NOW());

-- ========== PROMOTION ==========
INSERT INTO chatbot_trainings (question, answer, category, priority, status, created_at, updated_at) VALUES
('Có chương trình khuyến mãi gì không?', 
'Các chương trình khuyến mãi đang diễn ra:

**Flash Sale hàng ngày:**
- 10h-12h: Giảm 15% toàn bộ Nike
- 15h-17h: Giảm 20% toàn bộ Adidas
- 20h-22h: Giảm 10% toàn bộ sản phẩm

**Ưu đãi thành viên:**
- Member Bạc: Giảm 5% mọi đơn
- Member Vàng: Giảm 10% + Free ship
- Member Kim Cương: Giảm 15% + Free ship + Quà tặng

**Mã giảm giá:**
- WELCOME10: Giảm 10% đơn đầu
- FREESHIP: Miễn phí ship đơn từ 300k
- SALE50: Giảm 50k đơn từ 500k

Bạn muốn biết thêm về chương trình nào?', 
'PROMOTION', 10, 'ACTIVE', NOW(), NOW()),

('Làm thế nào để trở thành thành viên?', 
'Đăng ký thành viên rất đơn giản:

**Cách đăng ký:**
1. Truy cập website/app
2. Điền thông tin cá nhân
3. Xác nhận email/SĐT
4. Nhận mã thành viên ngay

**Quyền lợi:**
- Tích điểm mỗi đơn hàng (1đ = 1 điểm)
- Đổi điểm lấy quà
- Ưu đãi sinh nhật (Voucher 200k)
- Thông báo sale sớm
- Miễn phí đổi size

**Hạng thành viên:**
- Bạc: Mua từ 1 triệu
- Vàng: Mua từ 5 triệu
- Kim Cương: Mua từ 10 triệu

Bạn muốn đăng ký ngay không?', 
'PROMOTION', 9, 'ACTIVE', NOW(), NOW());

-- ========== WARRANTY ==========
INSERT INTO chatbot_trainings (question, answer, category, priority, status, created_at, updated_at) VALUES
('Giày có bảo hành không?', 
'Chính sách bảo hành:

**Thời gian:**
- Bảo hành 6 tháng với lỗi từ nhà sản xuất
- Bảo hành trọn đời đế giày (sửa chữa miễn phí)

**Các lỗi được bảo hành:**
- Đế bong, tróc
- Đường chỉ tuột
- Keo dán bong
- Lỗi vật liệu

**Không bảo hành:**
- Mòn tự nhiên do sử dụng
- Rách do va đập mạnh
- Hư hỏng do giặt sai cách

**Quy trình:**
1. Mang giày + hóa đơn đến cửa hàng
2. Kiểm tra lỗi
3. Sửa chữa hoặc đổi mới
4. Thời gian: 3-7 ngày

Giày của bạn gặp vấn đề gì?', 
'WARRANTY', 10, 'ACTIVE', NOW(), NOW());

-- ========== STORE INFO ==========
INSERT INTO chatbot_trainings (question, answer, category, priority, status, created_at, updated_at) VALUES
('Cửa hàng ở đâu?', 
'Hệ thống cửa hàng của chúng tôi:

**Hà Nội:**
- CN1: 123 Phố Huế, Hai Bà Trưng (8h-22h)
- CN2: 456 Cầu Giấy (8h-22h)
- CN3: 789 Thanh Xuân (9h-21h)

**Hồ Chí Minh:**
- CN1: 321 Nguyễn Trãi, Q1 (8h-22h)
- CN2: 654 Lê Văn Việt, Q9 (8h-22h)
- CN3: 987 Võ Văn Ngân, Thủ Đức (9h-21h)

**Đà Nẵng:**
- CN1: 111 Trần Phú, Hải Châu (8h-21h)

**Hotline:** 1900-xxxx (24/7)
**Email:** support@sneaker.com

Bạn muốn đến cửa hàng nào?', 
'STORE_INFO', 10, 'ACTIVE', NOW(), NOW());

-- ========== CARE GUIDE ==========
INSERT INTO chatbot_trainings (question, answer, category, priority, status, created_at, updated_at) VALUES
('Làm thế nào để vệ sinh giày?', 
'Hướng dẫn vệ sinh giày đúng cách:

**Giày da:**
1. Dùng khăn ẩm lau nhẹ
2. Dùng xi đánh bóng
3. Không giặt máy

**Giày vải/mesh:**
1. Tháo dây giày
2. Ngâm nước ấm + xà phòng 15 phút
3. Chải nhẹ bằng bàn chải mềm
4. Phơi khô tự nhiên (tránh nắng gắt)

**Giày trắng:**
1. Dùng baking soda + nước
2. Chải nhẹ
3. Lau sạch
4. Phơi khô

**Lưu ý:**
- Không giặt máy giày da
- Không phơi nắng trực tiếp
- Nhét giấy báo khi phơi để giữ form

Bạn cần vệ sinh loại giày gì?', 
'CARE_GUIDE', 9, 'ACTIVE', NOW(), NOW());
