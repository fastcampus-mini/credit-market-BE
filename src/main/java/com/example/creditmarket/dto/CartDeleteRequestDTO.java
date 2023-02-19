package com.example.creditmarket.dto;

import com.example.creditmarket.entity.EntityCart;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CartDeleteRequestDTO {

    List<Long> cartIds;

    public List<EntityCart> toEntity() {
        return cartIds.stream()
                .map(cartId -> EntityCart.builder()
                        .cartId(cartId)
                        .build())
                .collect(Collectors.toList());
    }
}
