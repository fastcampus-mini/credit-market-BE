package com.example.creditmarket.controller;

import com.example.creditmarket.dto.UserInfoResponseDTO;
import com.example.creditmarket.dto.UserLoginRequestDTO;
import com.example.creditmarket.dto.UserSignUpRequestDTO;
import com.example.creditmarket.entity.EntityUser;
import com.example.creditmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userservice;

    @PostMapping("/usersignup")
    public ResponseEntity<String> signup(UserSignUpRequestDTO request) {
        userservice.signup(request);
        return ResponseEntity.ok().body("SIGNUP_SUCCESS");
    }

    @PostMapping("/userlogin")
    public ResponseEntity<String> login(UserLoginRequestDTO request) {
        String token = userservice.login(request.getUserEmail(), request.getUserPassword());
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/AuthorizationTest")
    public ResponseEntity<String> review(Authentication authentication){
        return ResponseEntity.ok().body(authentication.getName() + " SUCCESS");
    }

    @PostMapping("/userlogout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        return ResponseEntity.ok().body("LOGOUT_SUCCESS");
    }

    @PostMapping("/userpasswordcheck")
    public ResponseEntity<EntityUser> userpasswordCheck(UserLoginRequestDTO request){
        EntityUser user = userservice.passwordCheck(request.getUserEmail(), request.getUserPassword());
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/userinfoupdate")
    public ResponseEntity<String> infoUpdate(UserSignUpRequestDTO request){
        userservice.infoUpdate(request.toEntity());
        return ResponseEntity.ok().body("INFO_UPDATE_SUCCESS");
    }
}
