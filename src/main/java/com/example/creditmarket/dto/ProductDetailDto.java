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
public class ProductDetailDto {
    //상품명, 개요, 대상, 한도, 금리 등의 상세정보 출력

    private String fproduct_id;
    private String fproduct_disclosure_month;
    private String fproduct_company_id;
    private String fproduct_company_name;
    private String fproduct_code;
    private String fproduct_name;
    private int fproduct_credit_product_type_code;
    private String fproduct_credit_product_type_name;
    private String fproduct_join_method;
    private String fproduct_credit_bureau_name ;
    private String fproduct_disclosure_start_month;
    private String fproduct_submit_day;
    private int fproduct_minimum_age;
    private String fproduct_target_gender;
    private String options_interest_type; //금리 구분
    private Double options_crdt_grad_avg; // 평균 금리

    public ProductDetailDto (EntityFProduct entity, EntityOption option){
        this.fproduct_id = entity.getFproduct_id();
        this.fproduct_code = entity.getFproduct_code();
        this.fproduct_company_id = entity.getFproduct_company_id();
        this.fproduct_credit_bureau_name = entity.getFproduct_credit_bureau_name();
        this.fproduct_credit_product_type_code = entity.getFproduct_credit_product_type_code();
        this.fproduct_credit_product_type_name = entity.getFproduct_credit_product_type_name();
        this.fproduct_disclosure_month = entity.getFproduct_disclosure_month();
        this.fproduct_disclosure_start_month = entity.getFproduct_disclosure_start_month();
        this.fproduct_join_method = entity.getFproduct_join_method();
        this.fproduct_minimum_age= entity.getFproduct_minimum_age();
        this.fproduct_name = entity.getFproduct_name();
        this.fproduct_submit_day = entity.getFproduct_submit_day();
        this.fproduct_target_gender = entity.getFproduct_target_gender();
        this.options_crdt_grad_avg = option.getOptions_crdt_grad_avg();
        this.options_interest_type = option.getOptions_interest_type();
        this.fproduct_company_name = entity.getFproduct_company_name();
    }
}
