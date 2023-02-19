package com.example.creditmarket.dto;

import com.example.creditmarket.entity.EntityOrder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderResponseDTO {

    private Long orderId;

    private Long orderStatus;

    private String fproductCompanyName;

    private String fproductName;

    private String fproductCreditProductTypeName;

    private int favorite; //int 1:0, boolean true:false 고민중

    public OrderResponseDTO(EntityOrder order) {
        this.orderId = order.getOrderId();
        this.orderStatus = order.getOrderStatus();
        this.fproductCompanyName = order.getFproduct().getFproduct_company_name();
        this.fproductName = order.getFproduct().getFproduct_name();
        this.fproductCreditProductTypeName = order.getFproduct().getFproduct_credit_product_type_name();
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}
