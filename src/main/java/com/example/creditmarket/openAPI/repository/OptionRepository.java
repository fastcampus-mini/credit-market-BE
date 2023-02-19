package com.example.creditmarket.openAPI.repository;

import com.example.creditmarket.openAPI.entity.EntityFProduct;
import com.example.creditmarket.openAPI.entity.EntityOption;
import com.example.creditmarket.openAPI.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface OptionRepository extends JpaRepository<EntityOption, Long> {

    @Query(value = "SELECT * FROM tb_fpoption op WHERE op.fproduct_id =:id AND op.options_interest_type =:type", nativeQuery = true)
    List<EntityOption> findOptionByProductIdAndType(@Param("id") String id, @Param("type") String type);

    @Query(value = "SELECT * FROM tb_fpoption op WHERE op.fproduct_id =:id AND options_interest_type = \'대출금리\'", nativeQuery = true)
    EntityOption findByProductId(@Param("id") String id);
}