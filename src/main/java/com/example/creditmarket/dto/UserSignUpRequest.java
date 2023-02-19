package com.example.creditmarket.dto;

import com.example.creditmarket.entity.EntityUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AllArgsConstructor
@Getter
public class UserSignUpRequest {


    private String userEmail;
    private String userPassword;
    private String userGender;
    private String userBirthDate;
    private String userJob;
    private String userPrefCreditProductTypeName;
    private String userPrefInterestType;
    private Long userCreditScore;

    public EntityUser toEntity(){
        return EntityUser.builder()
                .userEmail(userEmail)
                .userPassword(userPassword)
                .userGender(userGender)
                .userBirthdate(userBirthDate)
                .userJob(userJob)
                .userPrefCreditProductTypeName(userPrefCreditProductTypeName)
                .userPrefInterestType(userPrefInterestType)
                .userCreditScore(userCreditScore)
                .build();
    }

}
