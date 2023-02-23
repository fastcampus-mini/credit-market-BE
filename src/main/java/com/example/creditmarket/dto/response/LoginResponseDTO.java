package com.example.creditmarket.dto.response;

import com.example.creditmarket.entity.EntityUser;
import lombok.Getter;


@Getter
public class LoginResponseDTO {


    private String userEmail;

    private String token;

    public LoginResponseDTO(String userEmail, String token){
        this.userEmail = userEmail;
        this.token = token;

    }

}
