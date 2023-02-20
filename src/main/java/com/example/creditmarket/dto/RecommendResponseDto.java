package com.example.creditmarket.dto;

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
public class RecommendResponseDto {

    private String fproduct_id;

    private String company_name;

    private String product_name;

    private String product_type_name;

    private String options_interest_type;

    private double avg_interest;

    public RecommendResponseDto(EntityFProduct product, EntityOption option){
        this.fproduct_id = product.getFproduct_id();
        this.company_name = product.getFproduct_company_name();
        this.product_name = product.getFproduct_name();
        this.product_type_name = product.getFproduct_credit_product_type_name();
        this.options_interest_type = option.getOptions_interest_type();
        this.avg_interest = option.getOptions_crdt_grad_avg();
    }
}
