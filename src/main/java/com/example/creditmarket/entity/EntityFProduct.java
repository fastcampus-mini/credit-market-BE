package com.example.creditmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_fproduct")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class EntityFProduct {

    @Id
    @Column(name = "fproduct_id")
    private String fproduct_id;

    @Column(name = "fproduct_disclosure_month")
    private String fproduct_disclosure_month;
    @Column(name = "fproduct_company_id")
    private String fproduct_company_id;
    @Column(name = "fproduct_company_name")
    private String fproduct_company_name;
    @Column(name = "fproduct_code")
    private String fproduct_code;
    @Column(name = "fproduct_name")
    private String fproduct_name;
    @Column(name = "fproduct_credit_product_type_code")
    private int fproduct_credit_product_type_code;
    @Column(name = "fproduct_credit_product_type_name")
    private String fproduct_credit_product_type_name;
    @Column(name = "fproduct_join_method")
    private String fproduct_join_method;
    @Column(name = "fproduct_credit_bureau_name")
    private String fproduct_credit_bureau_name ;
    @Column(name = "fproduct_disclosure_start_month")
    private String fproduct_disclosure_start_month;
    @Column(name = "fproduct_submit_day")
    private String fproduct_submit_day;
    @Column(name = "fproduct_minimum_age")
    private int fproduct_minimum_age;
    @Column(name = "fproduct_target_gender")
    private String fproduct_target_gender;
}
