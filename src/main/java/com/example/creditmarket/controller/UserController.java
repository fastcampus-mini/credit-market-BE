package com.example.creditmarket.controller;

import com.example.creditmarket.dto.request.UserSendMailRequestDTO;
import com.example.creditmarket.dto.request.UserLoginRequestDTO;
import com.example.creditmarket.dto.request.UserSignUpRequestDTO;
import com.example.creditmarket.dto.response.LoginResponseDTO;
import com.example.creditmarket.entity.EntityUser;
import com.example.creditmarket.service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLoginRequestDTO request) {
        LoginResponseDTO loginReturn = userservice.login(request.getUserEmail(), request.getUserPassword());
        return ResponseEntity.ok().body(loginReturn);
    }

    @PostMapping("/AuthorizationTest")
    public ResponseEntity<String> review(@RequestBody Authentication authentication) {
        return ResponseEntity.ok().body(authentication.getName() + " SUCCESS");
    }

    @PostMapping("/userlogout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        userservice.logout(request);
        return ResponseEntity.ok().body("LOGOUT_SUCCESS");
    }

    @PostMapping("/userpasswordcheck")
    public ResponseEntity<EntityUser> userpasswordCheck(@RequestBody UserLoginRequestDTO request) {
        EntityUser user = userservice.passwordCheck(request.getUserEmail(), request.getUserPassword());
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/userinfoupdate")
    public ResponseEntity<String> infoUpdate(@RequestBody UserSignUpRequestDTO request) {
        userservice.infoUpdate(request.toEntity());
        return ResponseEntity.ok().body("INFO_UPDATE_SUCCESS");
    }

    @GetMapping("/userinfo")
    public ResponseEntity<EntityUser> getUserInfo(HttpServletRequest request) {
        EntityUser user = userservice.getUserInfo(request);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/sendmail")
    public ResponseEntity<String> sendMail(@RequestBody UserSendMailRequestDTO requestDTO) {
        String result = userservice.sendEmailAuth(requestDTO.getUserEmail());

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/newpassword")
    public ResponseEntity<String> sendNewPassword(@RequestBody UserSendMailRequestDTO requestDTO) {
        String newPassword = userservice.sendNewPasswordAuth(requestDTO.getUserEmail());

        return ResponseEntity.ok().body(newPassword);
    }
}
