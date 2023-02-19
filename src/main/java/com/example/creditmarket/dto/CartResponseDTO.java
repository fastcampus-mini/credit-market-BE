package com.example.creditmarket.dto;

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

    private int favorite; //int 1:0, boolean true:false 고민중

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public CartResponseDTO(EntityCart cart) {
        this.cartId = cart.getCartId();
        this.fproductCompanyName = cart.getFproduct().getFproduct_company_name();
        this.fproductName = cart.getFproduct().getFproduct_name();
        this.fproductCreditProductTypeName = cart.getFproduct().getFproduct_credit_product_type_name();
    }
}
