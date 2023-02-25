package com.example.creditmarket.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@Table(name = "tb_user")
@NoArgsConstructor
@Getter
@Builder
public class EntityUser {



    /*
    user_email
    user_password
    user_gender
    user_birthDate
    user_job
    user_pref_credit_product_type_name
    user_pref_interest_type
    user_credit_score
     */

    @Id
    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "user_birthdate")
    private String userBirthdate;

    @Column(name = "user_job")
    private String userJob;

    @Column(name = "user_pref_credit_product_type_name")
    private String userPrefCreditProductTypeName;

    @Column(name = "user_pref_interest_type")
    private String userPrefInterestType;

    @Column(name = "user_credit_score")
    private Long userCreditScore;

}
