package com.example.creditmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_jwt_logout")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityToken {

    @Id
    @Column(name= "user_token")
    private String token;
}
