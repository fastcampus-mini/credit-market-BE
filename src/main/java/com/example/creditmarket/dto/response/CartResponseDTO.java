package com.example.creditmarket.dto.response;

import com.example.creditmarket.entity.EntityCart;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CartResponseDTO {

    private Long cartId;

    private String companyName;

    private String productName;

    private String productId;

    private boolean favorite;

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Builder
    public CartResponseDTO(EntityCart cart) {
        this.cartId = cart.getCartId();
        this.companyName = cart.getFproduct().getFproduct_company_name();
        this.productName = cart.getFproduct().getFproduct_name();
        this.productId = cart.getFproduct().getFproduct_id();
    }
}
