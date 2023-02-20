package com.example.creditmarket.repository;

import com.example.creditmarket.entity.EntityOption;
import com.example.creditmarket.entity.EntityUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OptionRepository extends JpaRepository<EntityOption, Long> {
    @Query(value = "SELECT O, F " +
            "FROM EntityOption O " +
            "JOIN O.entityFProduct P " +
            "LEFT JOIN EntityFavorite F " +
            "ON F.fproduct = P.fproduct_id  " +
            "and (F.user = :userId or F.user IS NULL)   " +
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
    Page<Object[]> search(@Param("loan") String loan,
                                @Param("age") String age,
                                @Param("gender") String gender,
                                @Param("interest") String interest,
                                @Param("rate") Double rate,
                                @Param("keyword") String keyword,
                                @Param("userId") EntityUser userId,
                                Pageable pageable);

    @Query(value = "SELECT * FROM tb_fpoption op WHERE op.fproduct_id =:id AND op.options_interest_type =:type", nativeQuery = true)
    List<EntityOption> findOptionByProductIdAndType(@Param("id") String id, @Param("type") String type);

    @Query(value = "SELECT * FROM tb_fpoption op WHERE op.fproduct_id =:id AND options_interest_type = \'대출금리\'", nativeQuery = true)
    EntityOption findByProductId(@Param("id") String id);
}
