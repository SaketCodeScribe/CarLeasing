package com.car.leasing.service.impl;

import com.car.leasing.repository.CarRepository;
import com.car.leasing.repository.UserRepository;
import com.car.leasing.repository.entity.Car;
import com.car.leasing.repository.entity.User;
import com.car.leasing.service.CarLeaseService;
import com.car.leasing.utility.Utility;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CarLeaseServiceImpl implements CarLeaseService {

    private UserRepository userRepo;
    private CarRepository carRepo;

    public CarLeaseServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public CarLeaseServiceImpl(CarRepository carRepo) {
        this.carRepo = carRepo;
    }

    @Override
    public ResponseEntity<String> createAccount(User user, HttpServletResponse response){
        ResponseEntity<String> responseEntity;
        if (userRepo.findByEmail(user.getUserEmail()).isEmpty()){
            userRepo.save(user);
            HttpHeaders headers = new HttpHeaders();

            Cookie cookie = new Cookie("cookie", Utility.getEncryption(user));
            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setSecure(true);
            response.addCookie(cookie);
            responseEntity = new ResponseEntity(user, HttpStatus.CREATED);
        }
        else{
            responseEntity = new ResponseEntity("user already exists!", HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

//    @Override
//    public ResponseEntity<List<Car>> fetchCars(HttpServletRequest request){
//        User user request.getHeaderNames("userEmail")
//    }
}
