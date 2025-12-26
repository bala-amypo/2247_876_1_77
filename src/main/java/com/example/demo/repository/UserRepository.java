// package com.example.demo.repository;

// import com.example.demo.entity.*;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.time.Instant;
// import java.util.*;
// @Repository
// public interface UserRepository extends JpaRepository<User, Long> {
//     boolean existsByEmail(String email);
//     Optional<User> findByEmail(String email);
//     List<User> findByRole(User.Role role);
// }
package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {
}
