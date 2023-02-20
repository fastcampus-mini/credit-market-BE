package com.example.creditmarket.dto;

import com.example.creditmarket.entity.EntityFProduct;
import com.example.creditmarket.entity.EntityOrder;
import com.example.creditmarket.entity.EntityUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequestDto {

    private Long orderId;

    private int orderStatus = 1;

    private LocalDateTime orderDate = LocalDateTime.now();

    private EntityUser user;

    private EntityFProduct fproduct;

    @Builder
    public EntityOrder toEntity(){
        return EntityOrder.builder()
                .orderId(orderId)
                .orderStatus(orderStatus)
                .orderDate(orderDate)
                .user(user)
                .fproduct(fproduct)
                .build();
    }
}
