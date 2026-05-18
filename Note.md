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





Đây là nội dung tóm tắt lỗi 404 này để bạn copy vào file `Note.md` nhé:

---

## 5. Lỗi 404 Not Found (NoResourceFoundException) khi gọi API
*   **Dấu hiệu:** 
    *   Postman trả về `404 Not Found`.
    *   Log server báo lỗi: `org.springframework.web.servlet.resource.NoResourceFoundException: No static resource api/chat/message`.
*   **Nguyên nhân:** 
    1.  **Thiếu dấu gạch chéo (`/`):** Các khai báo `@RequestMapping` hoặc `@GetMapping` không bắt đầu bằng dấu `/` (ví dụ: `@RequestMapping("api/chat")` thay vì `@RequestMapping("/api/chat")`). Điều này khiến Spring Boot 3 đôi khi không nhận diện đúng đường dẫn API và quay sang tìm kiếm file tĩnh (static resources).
    2.  **Chưa lưu file hoặc chưa restart:** Controller mới viết nhưng chưa được lưu (Ctrl+S) hoặc server chưa được khởi động lại để cập nhật danh sách URL mới.
    3.  **Nhầm lẫn lỗi JWT:** Nếu lỗi là 404, thì **không phải do Token**. Nếu do Token (sai hoặc thiếu) thì lỗi phải là `401 Unauthorized` hoặc `403 Forbidden`.
*   **Cách khắc phục:** 
    *   Thêm dấu `/` vào trước tất cả các đường dẫn trong Controller:
        ```java
        @RequestMapping("/api/chat")
        @GetMapping("/message/{roomId}")
        ```
    *   Đảm bảo tất cả các file Java đã được lưu.
    *   Restart lại ứng dụng bằng lệnh `.\mvnw spring-boot:run`.

--- 

Bạn cứ lưu vào để sau này có bị lại thì "mò" cho nhanh!
