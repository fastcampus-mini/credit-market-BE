package com.example.creditmarket.controller;

import com.example.creditmarket.dto.request.UserLoginRequestDTO;
import com.example.creditmarket.dto.response.UserSignUpRequestDTO;
import com.example.creditmarket.entity.EntityUser;
import com.example.creditmarket.service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userservice;

    @PostMapping("/usersignup")
    public ResponseEntity<String> signup(@RequestBody UserSignUpRequestDTO request) {
        userservice.signup(request);
        return ResponseEntity.ok().body("SIGNUP_SUCCESS");
    }

    @PostMapping("/userlogin")
    public ResponseEntity<String> login(@RequestBody UserLoginRequestDTO request) {
        String token = userservice.login(request.getUserEmail(), request.getUserPassword());
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/AuthorizationTest")
    public ResponseEntity<String> review(@RequestBody Authentication authentication){
        return ResponseEntity.ok().body(authentication.getName() + " SUCCESS");
    }

    @PostMapping("/userlogout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        userservice.logout(request);
        return ResponseEntity.ok().body("LOGOUT_SUCCESS");
    }

    @PostMapping("/userpasswordcheck")
    public ResponseEntity<EntityUser> userpasswordCheck(@RequestBody UserLoginRequestDTO request){
        EntityUser user = userservice.passwordCheck(request.getUserEmail(), request.getUserPassword());
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/userinfoupdate")
    public ResponseEntity<String> infoUpdate(@RequestBody UserSignUpRequestDTO request){
        userservice.infoUpdate(request.toEntity());
        return ResponseEntity.ok().body("INFO_UPDATE_SUCCESS");
    }

    @GetMapping("/userinfo")
    public ResponseEntity<EntityUser> getUserInfo(HttpServletRequest request){
        EntityUser user = userservice.getUserInfo(request);
        return ResponseEntity.ok().body(user);
    }
}
