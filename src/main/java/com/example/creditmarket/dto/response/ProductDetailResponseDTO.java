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
public class ProductDetailResponseDTO {
    //상품명, 개요, 대상, 한도, 금리 등의 상세정보 출력

    private String productId;
    private String productName;
    private String companyName;
    private boolean favorite;
    private String productTypeName;
    private Double avgInterest; // 평균 금리
    private String optionsInterestType; //금리 구분
    private String productJoinMethod;

    public ProductDetailResponseDTO(EntityFProduct entity, EntityOption option, boolean isFavorite){
        this.companyName = entity.getFproduct_company_name();
        this.productId = entity.getFproduct_id();
        this.productTypeName = entity.getFproduct_credit_product_type_name();
        this.productJoinMethod = entity.getFproduct_join_method();
        this.productName = entity.getFproduct_name();
        this.avgInterest = option.getOptions_crdt_grad_avg();
        this.optionsInterestType = option.getOptions_interest_type();
        this.favorite = isFavorite;
    }
}
