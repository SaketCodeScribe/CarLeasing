package com.car.leasing.controller;

import com.car.leasing.repository.CarRepository;
import com.car.leasing.repository.LeaseRepository;
import com.car.leasing.repository.entity.Car;
import com.car.leasing.repository.entity.User;
import com.car.leasing.service.CarLeaseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody User user){
        return carLeaseService.createAccount(user);
    }

    /*
        Fetch cars
     */
    @GetMapping("/fetch-cars")
    public ResponseEntity<List<Car>> fetchCars(HttpServletRequest request){
        return carLeaseService.fetchCars(request);
    }

}
