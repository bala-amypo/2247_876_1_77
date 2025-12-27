
// package com.example.demo.serviceimpl;

// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import java.util.List;

// public class UserServiceImpl {

//     private final UserRepository repo;
//     private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//     public UserServiceImpl(UserRepository repo) {
//         this.repo = repo;
//     }

//     public User register(User user) {
//         if (repo.existsByEmail(user.getEmail())) {
//             throw new IllegalArgumentException("Email already exists");
//         }

//         user.setPassword(encoder.encode(user.getPassword()));

//         if (user.getRole() == null) {
//             user.setRole(User.Role.STUDENT);
//         }

//         return repo.save(user);
//     }

//     public User getById(Long id) {
//         return repo.findById(id)
//                 .orElseThrow(() -> new RuntimeException("user not found"));
//     }

//     public User findByEmail(String email) {
//         return repo.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("user not found"));
//     }

//     public List<User> listInstructors() {
//         return repo.findByRole(User.Role.INSTRUCTOR);
//     }
// }
package com.example.demo.serviceimpl;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(encoder.encode(request.getPassword()))
                .role(
                    request.getRole() != null
                        ? request.getRole()
                        : User.Role.STUDENT
                )
                .build();

        return userRepository.save(user);
    }

    @Override
    public User login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
