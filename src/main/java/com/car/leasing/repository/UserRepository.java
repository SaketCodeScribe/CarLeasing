package com.car.leasing.repository;

import com.car.leasing.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {
    List<String> findByEmail(String email);
}
