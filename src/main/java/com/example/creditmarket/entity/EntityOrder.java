package com.example.creditmarket.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_order")
@Data
@Builder
@RequiredArgsConstructor
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
    private Integer orderStatus;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @ManyToOne(targetEntity = EntityUser.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_email")
    private EntityUser user;

    @ManyToOne(targetEntity = EntityFProduct.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "fproduct_id")
    private EntityFProduct fproduct;

    @Builder
    public EntityOrder(Long orderId, int orderStatus, LocalDateTime orderDate, EntityUser user, EntityFProduct product){
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.user = user;
        this.fproduct = product;
    }
}
