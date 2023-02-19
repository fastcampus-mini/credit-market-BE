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
    private Long orderId;

    @Column(name = "order_status")
    private Long orderStatus;

    @Column(name = "order_date")
    private String orderDate;

    @ManyToOne(targetEntity = EntityUser.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_email")
    private EntityUser user;

    @ManyToOne(targetEntity = EntityFProduct.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "fproduct_id")
    private EntityFProduct fproduct;
}
