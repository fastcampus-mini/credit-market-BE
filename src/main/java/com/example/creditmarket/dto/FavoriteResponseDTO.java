package com.example.creditmarket.dto;

import com.example.creditmarket.entity.EntityFavorite;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FavoriteResponseDTO {

    private Long favoriteId;

    private String fproductCompanyName;

    private String fproductName;

    private String fproductCreditProductTypeName;

    public FavoriteResponseDTO(EntityFavorite favorite) {
        this.favoriteId = favorite.getFavoriteId();
        this.fproductCompanyName = favorite.getFproduct().getFproduct_company_name();
        this.fproductName = favorite.getFproduct().getFproduct_name();
        this.fproductCreditProductTypeName = favorite.getFproduct().getFproduct_credit_product_type_name();
    }
}
