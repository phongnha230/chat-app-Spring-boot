package com.chatapp.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import java.time.LocalDateTime;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
    private String id;

  @Indexed(unique = true)
    private String name;

  @Indexed(unique = true)
    private String email;

  private String password;

  private LocalDateTime createAt;

  @Builder.Default
    private boolean enabled =true;

  //@Builder là một Design Pattern (Builder Pattern) giúp tạo object linh hoạt hơn, đặc biệt khi class có nhiều fields!
}
