package com.example.creditmarket.controller;

import com.example.creditmarket.dto.request.UserLoginRequestDTO;
import com.example.creditmarket.dto.request.UserSignUpRequestDTO;
import com.example.creditmarket.dto.response.LoginResponseDTO;
import com.example.creditmarket.entity.EntityUser;
import com.example.creditmarket.exception.AppException;
import com.example.creditmarket.exception.ErrorCode;
import com.example.creditmarket.openAPI.CrawlingOpenAPI;
import com.example.creditmarket.service.Impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
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
    UserServiceImpl userServiceImpl;
    @MockBean
    SearchController searchController;
    @MockBean
    CartController cartController;
    @MockBean
    MyPageController myPageController;
    @MockBean
    ProductController productController;
    @MockBean
    CrawlingOpenAPI crawlingOpenAPI;
    @Autowired
    ObjectMapper objectMapper;


    @Test
    @DisplayName("/usersignup ???????????? ??????")
    @WithMockUser
    void signup() throws Exception{
        String userEmail = "test@Email.com";
        String userPassword = "testPassword";
        String userName ="??????";
        String userGender = "???";
        String userBirthDate = "20010101";
        String userJob = "??????";
        String userPrefCreditProductTypeName = "????????????????????????";
        String userPrefInterestType = "????????????";
        Long userCreditScore = 700L;

        mockMvc.perform(post("/usersignup")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserSignUpRequestDTO(userEmail, userPassword, userName, userGender, userBirthDate, userJob, userPrefCreditProductTypeName, userPrefInterestType, userCreditScore))))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("/usersignup ???????????? ?????? - userName ??????")
    @WithMockUser
    void signup_fail() throws Exception{
        String userEmail = "test@Email.com";
        String userPassword = "testPassword";
        String userName ="??????";
        String userGender = "???";
        String userBirthDate = "20010101";
        String userJob = "??????";
        String userPrefCreditProductTypeName = "????????????????????????";
        String userPrefInterestType = "????????????";
        Long userCreditScore = 700L;

        when(userServiceImpl.signup(any()))
                .thenThrow(new RuntimeException("?????? userId??? ???????????????."));

        mockMvc.perform(post("/usersignup")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserSignUpRequestDTO(userEmail, userPassword,userName, userGender, userBirthDate, userJob, userPrefCreditProductTypeName, userPrefInterestType, userCreditScore))))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("/userlogin ????????? ??????")
    @WithMockUser
    void login_success() throws Exception {

        UserSignUpRequestDTO testUser = new UserSignUpRequestDTO(
                "test@Email.com",
                "testPassword",
                "??????",
                "???",
                "20010101",
                "??????",
                "????????????????????????",
                "????????????",
                500L);
        userServiceImpl.signup(testUser);

        String userEmail = "test@Email.com";
        String userPassword = "testPassword";


        when(userServiceImpl.login(any(), any()))
                .thenReturn(new LoginResponseDTO(userEmail, "token"));

        mockMvc.perform(post("/userlogin")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequestDTO(userEmail, userPassword))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("/userlogin ????????? ?????? - userName ??????")
    @WithMockUser
    void login_fail1() throws Exception {
        String userEmail = "test@Email.com";
        String userPassword = "testPassword";

        when(userServiceImpl.login(any(), any()))
                .thenThrow(new AppException(ErrorCode.USERMAIL_NOT_FOUND, ""));

        mockMvc.perform(post("/userlogin")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequestDTO(userEmail, userPassword))))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("/userlogin ????????? ?????? - password ??????")
    @WithMockUser
    void login_fail2() throws Exception {
        String userEmail = "test@Email.com";
        String userPassword = "testPassword";

        when(userServiceImpl.login(any(), any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD, ""));

        mockMvc.perform(post("/userlogin")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserLoginRequestDTO(userEmail, userPassword))))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("/userlogout ???????????? ??????")
    @WithMockUser
    void logout_success() throws Exception {
        mockMvc.perform(post("/userlogout")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("/userlogout ???????????? ?????? - ????????? ??????")
    void logout_fail() throws Exception {
        mockMvc.perform(post("/userlogout")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("/userpasswordcheck ?????? ???????????? ??????")
    @WithMockUser
    void checkPassword_success() throws Exception {
        UserSignUpRequestDTO testUser = new UserSignUpRequestDTO(
                "test@Email.com",
                "testPassword",
                "??????",
                "???",
                "20010101",
                "??????",
                "????????????????????????",
                "????????????",
                500L);
        userServiceImpl.signup(testUser);

        when(userServiceImpl.passwordCheck(any(), any()))
                .thenReturn(EntityUser.builder()
                        .userEmail("test@Email.com")
                        .userPassword("testPassword")
                        .userGender("???")
                        .userBirthdate("20010101")
                        .userJob("??????")
                        .userPrefCreditProductTypeName("????????????????????????")
                        .userPrefInterestType("????????????")
                        .userCreditScore(500L)
                        .build());

        String userEmail = "test@Email.com";
        String userPassword = "testPassword";
        mockMvc.perform(post("/userpasswordcheck")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserLoginRequestDTO(userEmail, userPassword))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("/userpasswordcheck ?????? ???????????? ?????? - password ??????")
    @WithMockUser
    void checkPassword_fail() throws Exception {
        UserSignUpRequestDTO testUser = new UserSignUpRequestDTO(
                "test@Email.com",
                "testPassword",
                "??????",
                "???",
                "20010101",
                "??????",
                "????????????????????????",
                "????????????",
                500L);
        userServiceImpl.signup(testUser);

        when(userServiceImpl.passwordCheck(any(), any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD, ""));


        String userEmail = "test@Email.com";
        String userPassword = "testPassword2";
        mockMvc.perform(post("/userpasswordcheck")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserLoginRequestDTO(userEmail, userPassword))))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }


    @Test
    @DisplayName("/userinfoupdate ???????????? ?????? ??????")
    @WithMockUser
    void update_success() throws Exception {
        UserSignUpRequestDTO testUser = new UserSignUpRequestDTO(
                "test@Email.com",
                "testPassword",
                "??????",
                "???",
                "20010101",
                "??????",
                "????????????????????????",
                "????????????",
                500L);

        userServiceImpl.signup(testUser);

        String userEmail = "test@Email.com";
        String userPassword = "testPassword2";
        String userName = "??????";
        String userGender = "???";
        String userBirthDate = "20020101";
        String userJob = "?????????";
        String userPrefCreditProductTypeName = "????????????????????????";
        String userPrefInterestType = "????????????";
        Long userCreditScore = 800L;

        mockMvc.perform(post("/userinfoupdate")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserSignUpRequestDTO(userEmail, userPassword, userName, userGender, userBirthDate, userJob, userPrefCreditProductTypeName, userPrefInterestType, userCreditScore))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("/userinfo ???????????? ?????? ??????")
    @WithMockUser
    public void testGetUserInfo() {
        UserSignUpRequestDTO testUser = new UserSignUpRequestDTO(
                "test@Email.com",
                "testPassword",
                "??????",
                "???",
                "20010101",
                "??????",
                "????????????????????????",
                "????????????",
                500L);

        userServiceImpl.signup(testUser);

        String userEmail = "test@Email.com";
        String userPassword = "testPassword";
        String userName = "??????";
        String userGender = "???";
        String userBirthDate = "20020101";
        String userJob = "?????????";
        String userPrefCreditProductTypeName = "????????????????????????";
        String userPrefInterestType = "????????????";
        Long userCreditScore = 800L;

        when(userServiceImpl.getUserInfo(any()))
                .thenReturn(EntityUser.builder()
                        .userEmail(userEmail)
                        .userPassword(userPassword)
                        .userName(userName)
                        .userGender(userGender)
                        .userBirthdate(userBirthDate)
                        .userJob(userJob)
                        .userPrefCreditProductTypeName(userPrefCreditProductTypeName)
                        .userPrefInterestType(userPrefInterestType)
                        .userCreditScore(userCreditScore)
                        .build());
    }

}