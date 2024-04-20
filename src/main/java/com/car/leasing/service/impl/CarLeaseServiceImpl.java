package com.car.leasing.service.impl;

import com.car.leasing.repository.CarRepository;
import com.car.leasing.repository.SessionRepository;
import com.car.leasing.repository.UserRepository;
import com.car.leasing.repository.entity.Car;
import com.car.leasing.repository.entity.Session;
import com.car.leasing.repository.entity.User;
import com.car.leasing.service.CarLeaseService;
import com.car.leasing.utility.Utility;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CarLeaseServiceImpl implements CarLeaseService {
    @Autowired
    private UserRepository userRepo;
    private CarRepository carRepo;
    @Autowired
    private SessionRepository sessionRepo;

    private Cookie cookie;
//    @Autowired
//    public CarLeaseServiceImpl(UserRepository userRepo) {
//        this.userRepo = userRepo;
//    }
//    @Autowired
//    public CarLeaseServiceImpl(CarRepository carRepo) {
//        this.carRepo = carRepo;
//    }

    public CarLeaseServiceImpl(){}

    @Override
    public ResponseEntity<User> createAccount(User user, HttpServletResponse response) throws IOException {
        ResponseEntity<User> responseEntity;
        User user_ = userRepo.findByUserEmail(user.getUserEmail());
        if (user_ == null){
            HttpHeaders headers = new HttpHeaders();
            Session session = Utility.getEncryption(user, sessionRepo);
            cookie = new Cookie("set-cookie", session.getCookie());
            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setSecure(true);
            cookie.setPath("/");
            response.addCookie(cookie);
            responseEntity = new ResponseEntity(user, HttpStatus.CREATED);
            userRepo.save(user);
            sessionRepo.save(session);
        }
        else
            responseEntity = new ResponseEntity(user_, HttpStatus.OK);
        response.sendRedirect("http://localhost:8080/profile");
        return responseEntity;
    }

    @Override
    public ResponseEntity<User> getProfile(HttpServletRequest request){
        /*
            Web browser can carry forward the cookies to redirected URL, but postman can't.
         */
        String cookies;
        if (cookie == null)
            cookies = request.getHeader("set-cookie");
        else
            cookies = cookie.getValue();
        User user = Optional.ofNullable(sessionRepo.findByCookie(cookies)).map(Session::getUser).orElse(null);
        if (user == null)
            return new ResponseEntity("user not logged in!", HttpStatus.FORBIDDEN);
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
