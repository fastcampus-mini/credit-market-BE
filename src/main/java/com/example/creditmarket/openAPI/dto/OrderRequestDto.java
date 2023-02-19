package com.example.creditmarket.openAPI.dto;

import com.example.creditmarket.openAPI.entity.EntityFProduct;
import com.example.creditmarket.openAPI.entity.EntityOrder;
import com.example.creditmarket.openAPI.entity.EntityUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequestDto {

    private Long order_id;

    private int order_status = 1;

    private LocalDateTime order_date = LocalDateTime.now();

    private EntityUser user;

    private EntityFProduct fProduct;

    @Builder
    public EntityOrder toEntity(){
        return EntityOrder.builder()
                .order_id(order_id)
                .order_status(order_status)
                .order_date(order_date)
                .user(user)
                .product(fProduct)
                .build();
    }
}
