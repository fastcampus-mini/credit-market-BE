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
    private String productDisclosureMonth;
    private String productCompanyId;
    private String productCompanyName;
    private String productCode;
    private String productName;
    private int productCreditProductTypeCode;
    private String productCreditProductTypeName;
    private String productJoinMethod;
    private String productCreditBureauName ;
    private String productDisclosureStartMonth;
    private String productSubmitDay;
    private int productMinimumAge;
    private String productTargetGender;
    private String optionsInterestType; //금리 구분
    private Double optionsCrdtGradAvg; // 평균 금리

    public ProductDetailResponseDTO(EntityFProduct entity, EntityOption option){
        this.productId = entity.getFproduct_id();
        this.productCode = entity.getFproduct_code();
        this.productCompanyId = entity.getFproduct_company_id();
        this.productCreditBureauName = entity.getFproduct_credit_bureau_name();
        this.productCreditProductTypeCode = entity.getFproduct_credit_product_type_code();
        this.productCreditProductTypeName = entity.getFproduct_credit_product_type_name();
        this.productDisclosureMonth = entity.getFproduct_disclosure_month();
        this.productDisclosureStartMonth = entity.getFproduct_disclosure_start_month();
        this.productJoinMethod = entity.getFproduct_join_method();
        this.productMinimumAge= entity.getFproduct_minimum_age();
        this.productName = entity.getFproduct_name();
        this.productSubmitDay = entity.getFproduct_submit_day();
        this.productTargetGender = entity.getFproduct_target_gender();
        this.productName = entity.getFproduct_company_name();
        this.optionsCrdtGradAvg = option.getOptions_crdt_grad_avg();
        this.optionsInterestType = option.getOptions_interest_type();
    }
}
