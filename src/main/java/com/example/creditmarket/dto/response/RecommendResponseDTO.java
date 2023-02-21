package com.example.creditmarket.dto.response;

import com.example.creditmarket.entity.EntityFProduct;
import com.example.creditmarket.entity.EntityOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RecommendResponseDTO {

    private String productId;

    private String companyName;

    private String productName;

    private String productTypeName;

    private String optionsInterestType;

    private double avgInterest;

    public RecommendResponseDTO(EntityFProduct product, EntityOption option){
        this.productId = product.getFproduct_id();
        this.companyName = product.getFproduct_company_name();
        this.productName = product.getFproduct_name();
        this.productTypeName = product.getFproduct_credit_product_type_name();
        this.optionsInterestType = option.getOptions_interest_type();
        this.avgInterest = option.getOptions_crdt_grad_avg();
    }
}
