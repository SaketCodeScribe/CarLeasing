package com.car.leasing.service;

import com.car.leasing.repository.entity.Car;
import com.car.leasing.repository.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import java.util.*;

public interface CarLeaseService {
    default ResponseEntity<String> createAccount(User user){
        return null;
    }
    default ResponseEntity<List<Car>> fetchCars(HttpServletRequest request){
        return null;
    }
}
