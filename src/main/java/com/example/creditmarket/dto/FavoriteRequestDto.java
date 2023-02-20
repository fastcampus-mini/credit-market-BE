package com.example.creditmarket.dto;

import com.example.creditmarket.entity.EntityFProduct;
import com.example.creditmarket.entity.EntityFavorite;
import com.example.creditmarket.entity.EntityUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FavoriteRequestDto {

    private EntityUser user;

    private EntityFProduct product;

    @Builder
    public EntityFavorite toEntity(EntityUser user, EntityFProduct product){
        return EntityFavorite.builder()
                .user(user)
                .fproduct(product)
                .build();
    }
}
