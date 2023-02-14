package com.example.creditmarket.openAPI.repository;

import com.example.openapi_project.entity.EntityFProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<EntityFProduct, String> {

    @Query("SELECT t1, t2 FROM EntityFProduct t1 JOIN EntityOption t2 ON t1.fproduct_id = t2.entityFProduct.fproduct_id")
    List<Object[]> selectAllFProducts();

    @Query("SELECT t1, t2 FROM EntityFProduct t1 JOIN EntityOption t2 ON t1.fproduct_id = t2.entityFProduct.fproduct_id WHERE t1.fproduct_company_name = :companyName")
    List<Object[]> selectByCompanyName(@Param("companyName") String companyName);

    @Query("SELECT t1, t2 FROM EntityFProduct t1 JOIN EntityOption t2 ON t1.fproduct_id = t2.entityFProduct.fproduct_id WHERE t1.fproduct_name = :fProductName")
    List<Object[]> selectByFProductName(@Param("fProductName") String fProductName);

    @Query("SELECT t1, t2 FROM EntityFProduct t1 JOIN EntityOption t2 ON t1.fproduct_id = t2.entityFProduct.fproduct_id WHERE t1.fproduct_credit_product_type_name = :loanType")
    List<Object[]> selectByLoanType(@Param("loanType") String loanType);

    @Query("SELECT t1, t2 FROM EntityFProduct t1 JOIN EntityOption t2 ON t1.fproduct_id = t2.entityFProduct.fproduct_id WHERE t2.options_interest_type = :interestType")
    List<Object[]> selectByInterestType(@Param("interestType") String interestType);


}
