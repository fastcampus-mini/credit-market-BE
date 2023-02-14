package com.example.creditmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_fpoption")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class EntityOption {

    @Id
    @Column(name = "option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long option_id;

    @ManyToOne(targetEntity = EntityFProduct.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "fproduct_id")
    private EntityFProduct entityFProduct;

    @Column(name = "options_interest_code")
    private String options_interest_code;
    @Column(name = "options_interest_type")
    private String options_interest_type;
    @Column(name = "options_crdt_grad_1")
    private Double options_crdt_grad_1;
    @Column(name = "options_crdt_grad_4")
    private Double options_crdt_grad_4;
    @Column(name = "options_crdt_grad_5")
    private Double options_crdt_grad_5;
    @Column(name = "options_crdt_grad_6")
    private Double options_crdt_grad_6;
    @Column(name = "options_crdt_grad_10")
    private Double options_crdt_grad_10;
    @Column(name = "options_crdt_grad_11")
    private Double options_crdt_grad_11;
    @Column(name = "options_crdt_grad_12")
    private Double options_crdt_grad_12 ;
    @Column(name = "options_crdt_grad_13")
    private Double options_crdt_grad_13;
    @Column(name = "options_crdt_grad_avg")
    private Double options_crdt_grad_avg;
}
