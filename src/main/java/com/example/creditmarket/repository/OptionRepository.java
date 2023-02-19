package com.example.creditmarket.repository;

import com.example.creditmarket.entity.EntityOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OptionRepository extends JpaRepository<EntityOption, Long> {
    @Query(value = "SELECT O " +
            "FROM EntityOption O " +
            "JOIN O.entityFProduct P " +
            "WHERE P.fproduct_credit_product_type_name LIKE %:loan% " +
            "AND CAST(P.fproduct_minimum_age AS string) LIKE %:age% " +
            "AND P.fproduct_target_gender LIKE %:gender%    " +
            "AND O.options_interest_type LIKE %:interest%   " +
            "AND O.options_crdt_grad_avg <= :rate   " +
            "AND (" +
            "   P.fproduct_company_name LIKE %:keyword% " +
            "   OR P.fproduct_name LIKE %:keyword%  "   +
            "   OR P.fproduct_credit_product_type_name LIKE %:keyword%  "   +
            "   OR O.options_interest_type LIKE %:keyword%  "   +
            ")  " +
            "ORDER BY O.options_crdt_grad_avg ASC  "
    )
    Page<EntityOption> search(@Param("loan") String loan,
                                @Param("age") String age,
                                @Param("gender") String gender,
                                @Param("interest") String interest,
                                @Param("rate") Double rate,
                                @Param("keyword") String keyword,
                                Pageable pageable);
}
