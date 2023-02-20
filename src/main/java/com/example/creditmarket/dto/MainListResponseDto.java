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
    private String interestType;    // 금리 유형
    private boolean favorite;   // 관심상품 등록 여부

}
