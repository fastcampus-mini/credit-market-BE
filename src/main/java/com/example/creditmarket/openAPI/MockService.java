package com.example.creditmarket.openAPI;

import java.util.List;


public interface MockService {

     List<Object[]> selectAllFProducts();

     List<Object[]> selectByCompanyName(String companyName);

     List<Object[]> selectByFProductName(String fProductName);

     List<Object[]> selectByLoanType(String loanType);

     List<Object[]> selectByInterestType(String interestType);
}
