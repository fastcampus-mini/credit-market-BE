package com.example.creditmarket.openAPI.dto;

import com.example.creditmarket.openAPI.entity.EntityFProduct;
import com.example.creditmarket.openAPI.entity.EntityFavorite;
import com.example.creditmarket.openAPI.entity.EntityUser;
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
                .fProduct(product)
                .build();
    }
}
