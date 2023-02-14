package com.example.creditmarket.openAPI;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "fproduct")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class EntityFProduct {

    @Id
    @Column(name = "fproductid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fproductid;

    @ManyToOne(targetEntity = EntityCompany.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "conoprdtnm")
    private EntityCompany entityCompany;

    @Column(name = "crdt_lend_rate_type")
    private String crdt_lend_rate_type;
    @Column(name = "crdt_lend_rate_type_nm")
    private String crdt_lend_rate_type_nm;
    @Column(name = "crdt_grad_1")
    private Double crdt_grad_1;
    @Column(name = "crdt_grad_4")
    private Double crdt_grad_4;
    @Column(name = "crdt_grad_5")
    private Double crdt_grad_5;
    @Column(name = "crdt_grad_6")
    private Double crdt_grad_6;
    @Column(name = "crdt_grad_10")
    private Double crdt_grad_10;
    @Column(name = "crdt_grad_11")
    private Double crdt_grad_11;
    @Column(name = "crdt_grad_12")
    private Double crdt_grad_12 ;
    @Column(name = "crdt_grad_13")
    private Double crdt_grad_13;
    @Column(name = "crdt_grad_avg")
    private Double crdt_grad_avg;
}
