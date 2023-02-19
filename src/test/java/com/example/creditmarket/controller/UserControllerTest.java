package com.example.creditmarket.controller;

import com.example.creditmarket.dto.UserLoginRequestDTO;
import com.example.creditmarket.dto.UserSignUpRequestDTO;
import com.example.creditmarket.exception.AppException;
import com.example.creditmarket.exception.ErrorCode;
import com.example.creditmarket.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;
    @Autowired
    ObjectMapper objectMapper;


    @Test
    @DisplayName("회원가입 성공")
    @WithMockUser
    void signup() throws Exception{
        String userEmail = "test@Email.com";
        String userPassword = "testPassword";
        String userGender = "남";
        String userBirthDate = "20010101";
        String userJob = "학생";
        String userPrefCreditProductTypeName = "마이너스한도대출";
        String userPrefInterestType = "대출금리";
        Long userCreditScore = 700L;

        mockMvc.perform(post("/usersignup")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserSignUpRequestDTO(userEmail, userPassword, userGender, userBirthDate, userJob, userPrefCreditProductTypeName, userPrefInterestType, userCreditScore))))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("회원가입 실패 - userName 중복")
    @WithMockUser
    void signup_fail() throws Exception{
        String userEmail = "test@Email.com";
        String userPassword = "testPassword";
        String userGender = "남";
        String userBirthDate = "20010101";
        String userJob = "학생";
        String userPrefCreditProductTypeName = "마이너스한도대출";
        String userPrefInterestType = "대출금리";
        Long userCreditScore = 700L;

        when(userService.signup(any()))
                .thenThrow(new RuntimeException("해당 userId가 중복됩니다."));

        mockMvc.perform(post("/usersignup")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserSignUpRequestDTO(userEmail, userPassword, userGender, userBirthDate, userJob, userPrefCreditProductTypeName, userPrefInterestType, userCreditScore))))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("로그인 성공")
    @WithMockUser
    void login_success() throws Exception {
        String userEmail = "test@Email.com";
        String userPassword = "testPassword";

        when(userService.login(any(), any()))
                .thenReturn("token");


        mockMvc.perform(post("/userlogin")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequestDTO(userEmail, userPassword))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 실패 - userName 없음")
    @WithMockUser
    void login_fail1() throws Exception {
        String userEmail = "test@Email.com";
        String userPassword = "testPassword";

        when(userService.login(any(), any()))
                .thenThrow(new AppException(ErrorCode.USERMAIL_NOT_FOUND, ""));


        mockMvc.perform(post("/userlogin")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequestDTO(userEmail, userPassword))))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("로그인 실패 - password 틀림")
    @WithMockUser
    void login_fail2() throws Exception {
        String userEmail = "test@Email.com";
        String userPassword = "testPassword";

        when(userService.login(any(), any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD, ""));


        mockMvc.perform(post("/userlogin")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserLoginRequestDTO(userEmail, userPassword))))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}