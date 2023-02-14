package com.example.creditmarket.openAPI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MockRepository extends JpaRepository<EntityCompany, String> {

    @Query("SELECT t1, t2 FROM EntityCompany t1 JOIN EntityFProduct t2 ON t1.conoprdtnm = t2.entityCompany")
    List<Object[]> selectAllFProducts();

    @Query("SELECT t1, t2 FROM EntityCompany t1 JOIN EntityFProduct t2 ON t1.conoprdtnm = t2.entityCompany WHERE t1.kor_co_nm = :companyName")
    List<Object[]> selectByCompanyName(@Param("companyName") String companyName);

    @Query("SELECT t1, t2 FROM EntityCompany t1 JOIN EntityFProduct t2 ON t1.conoprdtnm = t2.entityCompany WHERE t1.fin_prdt_nm = :fProductName")
    List<Object[]> selectByFProductName(@Param("fProductName") String fProductName);

    @Query("SELECT t1, t2 FROM EntityCompany t1 JOIN EntityFProduct t2 ON t1.conoprdtnm = t2.entityCompany WHERE t1.crdt_prdt_type_nm = :loanType")
    List<Object[]> selectByLoanType(@Param("loanType") String loanType);

    @Query("SELECT t1, t2 FROM EntityCompany t1 JOIN EntityFProduct t2 ON t1.conoprdtnm = t2.entityCompany WHERE t2.crdt_lend_rate_type_nm = :interestType")
    List<Object[]> selectByInterestType(@Param("interestType") String interestType);


}
