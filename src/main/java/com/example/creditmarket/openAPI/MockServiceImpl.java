package com.example.creditmarket.openAPI;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MockServiceImpl implements MockService {

    private final MockRepository mockRepository;


    @Override
    public List<Object[]> selectAllFProducts() {
        return mockRepository.selectAllFProducts();
    }

    @Override
    public List<Object[]> selectByCompanyName(String companyName) {
        return mockRepository.selectByCompanyName(companyName);
    }

    @Override
    public List<Object[]> selectByFProductName(String fProductName) {
        return mockRepository.selectByFProductName(fProductName);
    }

    @Override
    public List<Object[]> selectByLoanType(String loanType) {
        return mockRepository.selectByLoanType(loanType);
    }

    @Override
    public List<Object[]> selectByInterestType(String interestType) {
        return mockRepository.selectByInterestType(interestType);
    }

}
