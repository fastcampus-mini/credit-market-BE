package com.example.creditmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_order")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class EntityOrder {

    /*
    order_id
    order_status
    order_date
    user_email
    fproduct_id
     */
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @Column(name = "order_status")
    private Long order_status;

    @Column(name = "order_date")
    private String order_date;

    @ManyToOne(targetEntity = EntityUser.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_email")
    private String user_email;

    @ManyToOne(targetEntity = EntityFProduct.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "fproduct_id")
    private Long fproduct_id;
}
