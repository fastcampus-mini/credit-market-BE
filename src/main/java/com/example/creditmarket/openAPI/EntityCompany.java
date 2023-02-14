package com.example.creditmarket.openAPI;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class EntityCompany {

    @Id
    @Column(name = "conoprdtnm")
    private String conoprdtnm;

    @Column(name = "dcls_month")
    private String dcls_month;
    @Column(name = "fin_co_no")
    private String fin_co_no;
    @Column(name = "fin_prdt_cd")
    private String fin_prdt_cd;
    @Column(name = "crdt_prdt_type")
    private String crdt_prdt_type;
    @Column(name = "kor_co_nm")
    private String kor_co_nm;
    @Column(name = "fin_prdt_nm")
    private String fin_prdt_nm;
    @Column(name = "join_way")
    private String join_way;
    @Column(name = "cb_name")
    private String cb_name;
    @Column(name = "crdt_prdt_type_nm")
    private String crdt_prdt_type_nm ;
    @Column(name = "dcls_strt_day")
    private String dcls_strt_day;
    @Column(name = "dcls_end_day")
    private String dcls_end_day;
    @Column(name = "fin_co_subm_day")
    private String fin_co_subm_day;
}
