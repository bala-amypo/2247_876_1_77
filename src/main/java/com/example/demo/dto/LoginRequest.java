
// package com.example.demo.dto;

// public class LoginRequest {

//     private String email;
//     private String fullName;

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getFullName() {
//         return fullName;
//     }

//     public void setFullName(String fullName) {
//         this.fullName = fullName;
//     }
// }
package com.example.demo.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String fullName;
}
