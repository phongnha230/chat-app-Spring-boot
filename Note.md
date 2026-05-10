# Nhật ký Fix Lỗi Project Chat App

Tài liệu này ghi lại các lỗi gặp phải trong quá trình phát triển và cách xử lý.

## 1. Lỗi sai chính tả lệnh chạy (Typo)
*   **Dấu hiệu:** `[ERROR] No plugin found for prefix 'srping-boot'`
*   **Nguyên nhân:** Viết sai chữ `spring` thành `srping` trong lệnh chạy.
*   **Cách khắc phục:** Chạy đúng lệnh:
    ```powershell
    .\mvnw spring-boot:run
    ```

## 2. Lỗi không đọc được biến môi trường từ file .env
*   **Dấu hiệu:** 
    *   `APPLICATION FAILED TO START`
    *   `Reason: failed to convert java.lang.String to java.lang.Integer (caused by NumberFormatException: For input string: "${PORT}")`
*   **Nguyên nhân:** Spring Boot không mặc định tự đọc file `.env`. Placeholder `${PORT}` không tìm thấy biến thực tế nên bị coi là một chuỗi văn bản.
*   **Cách khắc phục:** 
    *   Thêm giá trị mặc định vào `application.properties`: `server.port=${PORT:8080}`.
    *   Hoặc sử dụng thư viện `springboot3-dotenv` (xem mục 3).

## 3. Cấu hình thư viện đọc .env (springboot3-dotenv)
*   **Lỗi gặp phải:** Thêm nhầm thư viện hoặc sai phiên bản dẫn đến lỗi `Could not resolve dependencies`.
*   **Cấu hình chuẩn trong pom.xml:**
    ```xml
    <dependency>
        <groupId>me.paulschwarz</groupId>
        <artifactId>springboot3-dotenv</artifactId>
        <version>5.1.0</version>
    </dependency>
    ```
*   **Tác dụng:** Tự động nạp các biến từ file `.env` ở thư mục gốc vào Spring Environment khi ứng dụng khởi động.

## 4. Lưu ý chung
*   **Lưu file:** Luôn nhấn **Ctrl + S** file `pom.xml` trước khi chạy lại lệnh.
*   **Định dạng .env:** Định dạng chuẩn là `KEY=VALUE` (không có khoảng trắng dư thừa).
*   **Plugin IntelliJ:** Nên cài plugin **EnvFile** để hỗ trợ chạy trực tiếp trên IDE.
