package com.car.leasing.repository;

import com.car.leasing.repository.entity.Session;
import com.car.leasing.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface SessionRepository extends JpaRepository<Session, Long> {
    public List<Session> findByUser(User user);
    public Session findByCookie(String cookie);
}
