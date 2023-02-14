package com.example.creditmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_user")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
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
    private String user_email;

    @Column(name = "user_password")
    private String user_password;

    @Column(name = "user_gender")
    private String user_gender;

    @Column(name = "user_birthDate")
    private String user_birthDate;

    @Column(name = "user_job")
    private String user_job;

    @Column(name = "user_pref_credit_product_type_name")
    private String user_pref_credit_product_type_name;

    @Column(name = "user_pref_interest_type")
    private String user_pref_interest_type;

    @Column(name = "user_credit_score")
    private int user_credit_score;

}
