package com.example.creditmarket.dto.response;

import com.example.creditmarket.entity.EntityUser;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class UserInfoResponseDTO {


    private String userEmail;
    private String userPassword;
    private String userGender;
    private String userBirthDate;
    private String userJob;
    private String userPrefCreditProductTypeName;
    private String userPrefInterestType;
    private Long userCreditScore;

    public UserInfoResponseDTO(EntityUser user){
        this.userEmail = user.getUserEmail();
        this.userPassword = user.getUserPassword();
        this.userGender = user.getUserGender();
        this.userBirthDate = user.getUserBirthdate();
        this.userJob = user.getUserJob();
        this.userPrefCreditProductTypeName = user.getUserPrefCreditProductTypeName();
        this.userPrefInterestType = user.getUserPrefInterestType();
        this.userCreditScore = user.getUserCreditScore();
    }

}
