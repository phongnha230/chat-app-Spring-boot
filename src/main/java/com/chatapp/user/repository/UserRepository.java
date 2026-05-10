package com.chatapp.user.repository;
import com.chatapp.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

//Ở đây, MongoRepository tự động cung cấp: save(), findById(), findAll(), deleteById()
public interface UserRepository extends MongoRepository<User, String>{
//    Spring Data phân tích tên method → tự viết query cho bạn
    Optional<User> findByName(String name);
    // → SELECT * FROM users WHERE username = ? LIMIT 1
    Optional<User> findByEmail(String email);

    boolean existsByName(String name);

    boolean existsByEmail(String email);
}
