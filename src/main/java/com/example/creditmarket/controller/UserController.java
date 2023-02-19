package com.example.creditmarket.controller;


import com.example.creditmarket.dto.UserLoginRequest;
import com.example.creditmarket.dto.UserSignUpRequest;
import com.example.creditmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userservice;

    @PostMapping("/usersignup")
    public ResponseEntity<String> signup(UserSignUpRequest request) {
        userservice.signup(request);
        return ResponseEntity.ok().body("SIGNUP_SUCCESS");
    }

    @PostMapping("/userlogin")
    public ResponseEntity<String> login(UserLoginRequest request) {
        String token = userservice.login(request.getUserEmail(), request.getUserPassword());
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/AuthorizationTest")
    public ResponseEntity<String> review(Authentication authentication){
        return ResponseEntity.ok().body(authentication.getName() + " SUCCESS");
    }

    @PostMapping("/userlogout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        userservice.logout(request);
        return ResponseEntity.ok().body("LOGOUT_SUCCESS");
    }
}
