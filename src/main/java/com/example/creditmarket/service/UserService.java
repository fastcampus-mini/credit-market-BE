package com.example.creditmarket.service;

import com.example.creditmarket.dto.request.UserSignUpRequestDTO;
import com.example.creditmarket.dto.response.LoginResponseDTO;
import com.example.creditmarket.entity.EntityUser;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    public String signup(UserSignUpRequestDTO request);

    public LoginResponseDTO login(String userEmail, String password);

    public Boolean isValid(String userToken);

    public String logout(HttpServletRequest request);

    public EntityUser passwordCheck(String userEmail, String password);

    public String infoUpdate(EntityUser user);

    public EntityUser getUserInfo(HttpServletRequest request);
}
