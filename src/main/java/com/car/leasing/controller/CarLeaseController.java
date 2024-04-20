package com.car.leasing.controller;

import com.car.leasing.repository.CarRepository;
import com.car.leasing.repository.LeaseRepository;
import com.car.leasing.repository.entity.Car;
import com.car.leasing.repository.entity.User;
import com.car.leasing.service.CarLeaseService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController("/api")
public class CarLeaseController {
    private CarRepository careRepo;
    private LeaseRepository leaseRepo;
    private CarLeaseService carLeaseService;

    public CarLeaseController(CarRepository careRepo, LeaseRepository leaseRepo, CarLeaseService carLeaseService) {
        this.careRepo = careRepo;
        this.leaseRepo = leaseRepo;
        this.carLeaseService = carLeaseService;
    }

    /*
        Onboard users
    */
    @PostMapping("/signup")
    public ResponseEntity<User> createAccount(@RequestBody User user, final HttpServletResponse response) throws IOException {
        return carLeaseService.createAccount(user, response);
    }

    /*
        Fetch cars
     */
    @GetMapping("/fetchcars")
    public ResponseEntity<List<Car>> fetchCars(HttpServletRequest request){
        List<Car> cars= new ArrayList<>();
        return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(HttpServletRequest request){
        return carLeaseService.getProfile(request);
    }

    @GetMapping("/hello")
    public void getHello(HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie("set-cookie", "abcdef1234");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect("https://www.google.com/");
    }
}
