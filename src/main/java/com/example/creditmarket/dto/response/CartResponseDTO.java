package com.example.creditmarket.dto.response;

import com.example.creditmarket.entity.EntityCart;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CartResponseDTO {

    private Long cartId;

    private String fproductCompanyName;

    private String fproductName;

    private String fproductCreditProductTypeName;

    private boolean favorite;

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public CartResponseDTO(EntityCart cart) {
        this.cartId = cart.getCartId();
        this.fproductCompanyName = cart.getFproduct().getFproduct_company_name();
        this.fproductName = cart.getFproduct().getFproduct_name();
        this.fproductCreditProductTypeName = cart.getFproduct().getFproduct_credit_product_type_name();
    }
}
