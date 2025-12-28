
// package com.example.demo.dto;

// import lombok.*;

// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class RegisterRequest {
//     private String fullName;
//     private String email;
//     private String password;
//     private String role;
// }
package com.example.demo.dto;

import com.example.demo.entity.User.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String fullName;
    private Role role;
}
