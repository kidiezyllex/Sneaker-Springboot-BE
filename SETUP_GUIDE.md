# Hướng dẫn Cài đặt và Chạy Project

## Yêu cầu Hệ thống

### 1. Java Development Kit (JDK)
- **Phiên bản**: JDK 17 hoặc cao hơn ✅ (Java 25.0.1 đã được cài đặt)
- **Kiểm tra**: 
  ```bash
  java -version
  # hoặc
  java --version
  ```
- **Tải về**: [Oracle JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) hoặc [OpenJDK 17](https://adoptium.net/)
- **Lưu ý**: Nếu đã cài Java 25.0.1 hoặc cao hơn, bạn đã đáp ứng yêu cầu!

### 2. Apache Maven
- **Phiên bản**: 3.6.3 hoặc cao hơn
- **Kiểm tra**: 
  ```bash
  mvn -version
  ```
- **Tải về**: [Maven Download](https://maven.apache.org/download.cgi)
- **Cài đặt**: Thêm Maven vào PATH environment variable

### 3. MySQL Database
- **Phiên bản**: MySQL 8.0 hoặc cao hơn
- **Tải về**: [MySQL Download](https://dev.mysql.com/downloads/mysql/)
- **Hoặc sử dụng**: XAMPP, WAMP, hoặc Docker

### 4. IDE (Tùy chọn nhưng khuyến nghị)
- **Cursor** (Đã sử dụng) ✅
- IntelliJ IDEA
- Eclipse
- VS Code với Java Extension Pack

**Lưu ý cho Cursor**: Cursor dựa trên VS Code, nên cần cài đặt Java Extension Pack để hỗ trợ Java development.

---

## Các Bước Cài đặt

### Bước 1: Clone hoặc Tải Project
```bash
# Nếu có Git repository
git clone <repository-url>
cd sneakerBE

# Hoặc giải nén file ZIP
```

### Bước 2: Tạo Database MySQL

1. Mở MySQL Command Line hoặc MySQL Workbench
2. Tạo database mới:
   ```sql
   CREATE DATABASE sneakerDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```
3. Kiểm tra database đã tạo:
   ```sql
   SHOW DATABASES;
   ```

### Bước 3: Cấu hình Database trong `application.properties`

Mở file `src/main/resources/application.properties` và cập nhật thông tin database:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/sneakerDB?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root          # Thay đổi theo username của bạn
spring.datasource.password=              # Thay đổi theo password của bạn
```

**Lưu ý**: 
- Nếu MySQL chạy trên port khác (không phải 3306), thay đổi trong URL
- Nếu có password, điền vào trường `password`

### Bước 4: Cấu hình Cloudinary (Cho Upload Ảnh)

1. Đăng ký tài khoản tại [Cloudinary](https://cloudinary.com/)
2. Lấy thông tin từ Dashboard:
   - Cloud Name
   - API Key
   - API Secret

3. Cập nhật trong `application.properties`:
   ```properties
   cloudinary.cloud-name=your-cloud-name
   cloudinary.api-key=your-api-key
   cloudinary.api-secret=your-api-secret
   ```

**Hoặc** sử dụng Environment Variables (Khuyến nghị cho production):
```bash
# Windows (PowerShell)
$env:CLOUDINARY_CLOUD_NAME="your-cloud-name"
$env:CLOUDINARY_API_KEY="your-api-key"
$env:CLOUDINARY_API_SECRET="your-api-secret"

# Linux/Mac
export CLOUDINARY_CLOUD_NAME="your-cloud-name"
export CLOUDINARY_API_KEY="your-api-key"
export CLOUDINARY_API_SECRET="your-api-secret"
```

### Bước 5: Cấu hình Gemini AI (Cho Chatbot - Tùy chọn)

1. Lấy API Key từ [Google AI Studio](https://makersuite.google.com/app/apikey)
2. Cập nhật trong `application.properties`:
   ```properties
   gemini.api.key=your-gemini-api-key-here
   ```

**Hoặc** sử dụng Environment Variable:
```bash
# Windows (PowerShell)
$env:GEMINI_API_KEY="your-gemini-api-key"

# Linux/Mac
export GEMINI_API_KEY="your-gemini-api-key"
```

### Bước 6: Cài đặt Dependencies với Maven

Mở terminal/command prompt tại thư mục project và chạy:

```bash
# Windows
mvn clean install

# Linux/Mac
mvn clean install
```

Lệnh này sẽ:
- Tải tất cả dependencies từ Maven Central Repository
- Compile source code
- Chạy tests (nếu có)
- Tạo JAR file

**Lưu ý**: Lần đầu tiên có thể mất vài phút để tải dependencies.

### Bước 7: Chạy Project

#### Cách 1: Sử dụng Maven (Khuyến nghị)
```bash
mvn spring-boot:run
```

#### Cách 2: Sử dụng JAR file
```bash
# Build JAR file
mvn clean package

# Chạy JAR file
java -jar target/sneaker-be-1.0.0.jar
```

#### Cách 3: Chạy từ IDE

**Với Cursor (hoặc VS Code)**:
1. Mở project trong Cursor
2. Cài đặt **Java Extension Pack** (nếu chưa có):
   - Mở Extensions (Ctrl+Shift+X)
   - Tìm "Extension Pack for Java" (Microsoft)
   - Click Install
3. Mở file `src/main/java/com/sneaker/sneakerApplication.java`
4. Click vào nút "Run" ở trên class declaration
   - Hoặc click chuột phải → "Run Java"
   - Hoặc sử dụng Command Palette (Ctrl+Shift+P) → "Java: Run"

**Với IntelliJ IDEA hoặc Eclipse**:
1. Mở project trong IDE
2. Tìm file `sneakerApplication.java`
3. Click chuột phải → Run 'sneakerApplication'

### Bước 8: Kiểm tra Application đã chạy

1. Mở trình duyệt và truy cập:
   - **Swagger UI**: http://localhost:8080/swagger-ui.html
   - **API Docs**: http://localhost:8080/api-docs
   - **Health Check**: http://localhost:8080/actuator/health (nếu có actuator)

2. Nếu thấy Swagger UI, nghĩa là application đã chạy thành công!

---

## Cấu trúc Project

```
sneakerBE/
├── pom.xml                          # Maven configuration
├── src/
│   └── main/
│       ├── java/
│       │   └── com/sneaker/
│       │       ├── sneakerApplication.java  # Main class
│       │       ├── config/                     # Configuration classes
│       │       ├── controller/                 # REST Controllers
│       │       ├── service/                    # Business logic
│       │       ├── repository/                 # Data access layer
│       │       ├── entity/                     # JPA Entities
│       │       ├── dto/                        # Data Transfer Objects
│       │       ├── security/                   # Security configuration
│       │       ├── exception/                 # Exception handlers
│       │       └── util/                      # Utility classes
│       └── resources/
│           └── application.properties          # Application configuration
└── target/                                    # Build output (tự động tạo)
```

---

## Các Endpoint Chính

### Authentication
- `POST /api/auth/register` - Đăng ký
- `POST /api/auth/login` - Đăng nhập
- `GET /api/auth/me` - Lấy thông tin user hiện tại

### Products
- `GET /api/products` - Danh sách sản phẩm
- `GET /api/products/{id}` - Chi tiết sản phẩm
- `POST /api/products` - Tạo sản phẩm (Admin)
- `PUT /api/products/{id}` - Cập nhật sản phẩm (Admin)

### Orders
- `GET /api/orders` - Danh sách đơn hàng
- `POST /api/orders` - Tạo đơn hàng
- `GET /api/orders/{id}` - Chi tiết đơn hàng

### Vouchers
- `GET /api/vouchers` - Danh sách voucher
- `POST /api/vouchers` - Tạo voucher (Admin)
- `POST /api/vouchers/validate` - Validate voucher

**Xem đầy đủ API tại Swagger UI**: http://localhost:8080/swagger-ui.html

---

## Xử lý Lỗi Thường Gặp

### 1. Lỗi: "Cannot connect to database"
**Nguyên nhân**: MySQL chưa chạy hoặc thông tin kết nối sai
**Giải pháp**:
- Kiểm tra MySQL đã chạy chưa
- Kiểm tra username/password trong `application.properties`
- Kiểm tra database `sneakerDB` đã tạo chưa

### 2. Lỗi: "Port 8080 already in use"
**Nguyên nhân**: Port 8080 đã được sử dụng bởi ứng dụng khác
**Giải pháp**:
- Thay đổi port trong `application.properties`:
  ```properties
  server.port=8081
  ```
- Hoặc tắt ứng dụng đang sử dụng port 8080

### 3. Lỗi: "Java version not supported"
**Nguyên nhân**: Java version không đúng
**Giải pháp**:
- Cài đặt JDK 17 hoặc cao hơn
- Kiểm tra `JAVA_HOME` environment variable

### 4. Lỗi: "Maven not found"
**Nguyên nhân**: Maven chưa được cài đặt hoặc chưa thêm vào PATH
**Giải pháp**:
- Cài đặt Maven
- Thêm Maven vào PATH environment variable

### 5. Lỗi: "Cloudinary upload failed"
**Nguyên nhân**: Cloudinary credentials chưa được cấu hình
**Giải pháp**:
- Cập nhật Cloudinary credentials trong `application.properties`
- Hoặc sử dụng environment variables

### 6. Lỗi: "JWT secret key not found"
**Nguyên nhân**: JWT secret chưa được cấu hình
**Giải pháp**:
- Kiểm tra `jwt.secret` trong `application.properties`
- Đảm bảo secret key đủ mạnh (ít nhất 256 bits)

---

## Environment Variables (Khuyến nghị cho Production)

Tạo file `.env` hoặc set environment variables:

```bash
# Database
DB_URL=jdbc:mysql://localhost:3306/sneakerDB
DB_USERNAME=root
DB_PASSWORD=your-password

# JWT
JWT_SECRET=your-very-secure-secret-key-here
JWT_EXPIRATION=86400000

# Cloudinary
CLOUDINARY_CLOUD_NAME=your-cloud-name
CLOUDINARY_API_KEY=your-api-key
CLOUDINARY_API_SECRET=your-api-secret

# Gemini AI
GEMINI_API_KEY=your-gemini-api-key
```

Sau đó cập nhật `application.properties` để đọc từ environment variables:
```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
jwt.secret=${JWT_SECRET}
cloudinary.cloud-name=${CLOUDINARY_CLOUD_NAME}
cloudinary.api-key=${CLOUDINARY_API_KEY}
cloudinary.api-secret=${CLOUDINARY_API_SECRET}
gemini.api.key=${GEMINI_API_KEY}
```

---

## Development Tips

### 1. Cấu hình Cursor cho Java Development

**Cài đặt Extensions cần thiết**:
1. Mở Extensions (Ctrl+Shift+X hoặc Cmd+Shift+X)
2. Cài đặt các extensions sau:
   - **Extension Pack for Java** (Microsoft) - Bộ extension Java đầy đủ
   - **Spring Boot Extension Pack** (VMware) - Hỗ trợ Spring Boot
   - **Maven for Java** (Microsoft) - Hỗ trợ Maven

**Cấu hình Java trong Cursor**:
1. Mở Settings (Ctrl+,)
2. Tìm "java.home" và cấu hình đường dẫn đến JDK:
   ```json
   {
     "java.home": "C:\\Program Files\\Java\\jdk-25.0.1"
   }
   ```
   (Thay đổi đường dẫn theo vị trí JDK của bạn)

**Sử dụng Terminal trong Cursor**:
- Mở Terminal: Ctrl+` (backtick)
- Terminal tích hợp sẵn PowerShell trên Windows
- Có thể chạy Maven commands trực tiếp

### 2. Hot Reload
Project đã có `spring-boot-devtools` dependency, tự động reload khi code thay đổi.

### 3. Logging
Xem logs trong console hoặc file log (nếu được cấu hình).

### 4. Database Schema
- Hibernate sẽ tự động tạo/validate schema dựa trên entities
- Mode: `spring.jpa.hibernate.ddl-auto=validate` (không tự động tạo, chỉ validate)
- Để tự động tạo schema: đổi thành `update` hoặc `create`

### 5. Testing API
- Sử dụng Swagger UI: http://localhost:8080/swagger-ui.html
- Sử dụng Postman
- Sử dụng cURL
- Sử dụng REST Client extension trong Cursor

---

## Production Deployment

### 1. Build JAR file
```bash
mvn clean package -DskipTests
```

### 2. Chạy JAR file
```bash
java -jar target/sneaker-be-1.0.0.jar
```

### 3. Sử dụng Process Manager
- **PM2** (Node.js ecosystem)
- **systemd** (Linux)
- **Docker** (Container)

### 4. Cấu hình Production
- Đổi `spring.jpa.hibernate.ddl-auto=validate`
- Tắt debug logging
- Sử dụng environment variables cho sensitive data
- Cấu hình CORS đúng cách
- Sử dụng HTTPS

---

## Liên hệ và Hỗ trợ

Nếu gặp vấn đề, vui lòng:
1. Kiểm tra logs trong console
2. Kiểm tra file `application.properties`
3. Kiểm tra database connection
4. Xem Swagger UI để test API

---

## Tóm tắt Nhanh

```bash
# 1. ✅ Java 25.0.1 đã được cài đặt (đủ yêu cầu)

# 2. Cài đặt Maven và MySQL (nếu chưa có)
# Kiểm tra Maven:
mvn -version

# 3. Tạo database
mysql -u root -p
CREATE DATABASE sneakerDB;

# 4. Cấu hình application.properties
# - Cập nhật database credentials
# - Cấu hình Cloudinary (nếu cần upload ảnh)
# - Cấu hình Gemini AI (nếu cần chatbot)

# 5. Cài đặt Java Extensions trong Cursor
# - Extension Pack for Java
# - Spring Boot Extension Pack

# 6. Cài đặt dependencies
mvn clean install

# 7. Chạy application
mvn spring-boot:run
# Hoặc chạy từ Cursor: Mở sneakerApplication.java → Run

# 8. Truy cập Swagger UI
# http://localhost:8080/swagger-ui.html
```

## Checklist Cài đặt

- [x] ✅ Java 25.0.1 đã cài đặt
- [ ] ⬜ Maven đã cài đặt và cấu hình
- [ ] ⬜ MySQL đã cài đặt và chạy
- [ ] ⬜ Database `sneakerDB` đã tạo
- [ ] ⬜ Đã cấu hình `application.properties`
- [ ] ⬜ Đã cài Java Extensions trong Cursor
- [ ] ⬜ Đã chạy `mvn clean install`
- [ ] ⬜ Application đã chạy thành công

Chúc bạn thành công! 🚀

