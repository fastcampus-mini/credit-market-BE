package com.example.creditmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_favorite")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class EntityFavorite {

    /*
    favorite_id
    user_email
    fproduct_id
     */
    @Id
    @Column(name = "favorite_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String favorite_id;

    @ManyToOne(targetEntity = EntityUser.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_email")
    private String user_email;

    @ManyToOne(targetEntity = EntityFProduct.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "fproduct_id")
    private Long fproduct_id;
}