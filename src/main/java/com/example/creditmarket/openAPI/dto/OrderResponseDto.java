package com.example.creditmarket.openAPI.dto;

import com.example.creditmarket.openAPI.entity.EntityFProduct;
import com.example.creditmarket.openAPI.entity.EntityUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderResponseDto {

    private Long order_id;

    private int order_status;

    private LocalDateTime order_date;

    private EntityUser user;

    private EntityFProduct fProduct;
}
