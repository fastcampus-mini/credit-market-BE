package com.example.creditmarket.openAPI.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder

public class TestResponse {

    private int fproduct_id;
    private String conoprdtnm;
    private String dcls_month;
    private String fin_co_no;
    private String fin_prdt_cd;
    private String crdt_prdt_type;
    private String kor_co_nm;
    private String fin_prdt_nm;
    private String join_way;
    private String cb_name;
    private String crdt_prdt_type_nm;
    private String dcls_strt_day;
    private String dcls_end_day;
    private String fin_co_subm_day;
    private String crdt_lend_rate_type;
    private String crdt_lend_rate_type_nm;
    private Double crdt_grad_1;
    private Double crdt_grad_4;
    private Double crdt_grad_5;
    private Double crdt_grad_6;
    private Double crdt_grad_10;
    private Double crdt_grad_11;
    private Double crdt_grad_12;
    private Double crdt_grad_13;
    private Double crdt_grad_avg;
}
