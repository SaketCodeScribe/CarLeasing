package com.car.leasing.service;

import com.car.leasing.repository.entity.Car;
import com.car.leasing.repository.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.*;

public interface CarLeaseService {
    default ResponseEntity<User> createAccount(User user, HttpServletResponse response) throws IOException {
        return null;
    }
    default ResponseEntity<List<Car>> fetchCars(HttpServletRequest request){
        return null;
    }
    default ResponseEntity<User> getProfile(HttpServletRequest request){
        return null;
    }
}
