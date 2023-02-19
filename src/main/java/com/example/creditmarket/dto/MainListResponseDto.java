package com.example.creditmarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MainListResponseDto {
    private String companyName; // 은행
    private String productName; // 대출 상품
    private String productTypeName; // 대출 종류
    private Double interestRateAvg; // 평균 금리

}
