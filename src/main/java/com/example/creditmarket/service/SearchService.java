package com.example.creditmarket.service;

import com.example.creditmarket.dto.MainListResponseDto;
import com.example.creditmarket.entity.EntityOption;
import com.example.creditmarket.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final OptionRepository optionRepository;

    /**
     * 키워드와 카테고리 검색 서비스
     * @param keyword : 검색 키워드
     * @param loan : 대출종류
     * @param age : 나이
     * @param gender : 성별
     * @param interest : 금리유형
     * @param rate : 평균금리
     * @return
     */
    public List<MainListResponseDto> searchResult(String keyword, String loan, String age,
                                                  String gender, String interest, Double rate,
                                                  int page) {
        Pageable pageable = PageRequest.of(page, 10);
        if (rate == 0 || rate == null) rate = 20.0;
        Page<EntityOption> result = optionRepository.search(loan, age, gender, interest, rate, keyword, pageable);
        List<EntityOption> lists = result.getContent();
        if (lists != null || lists.size() != 0) {
            List<MainListResponseDto> mainListResponseListDto = new ArrayList<>();
            for (int i = 0; i < lists.size(); i++) {
                mainListResponseListDto.add(i, new MainListResponseDto(lists.get(i).getEntityFProduct().getFproduct_company_name(),
                        lists.get(i).getEntityFProduct().getFproduct_name(),
                        lists.get(i).getEntityFProduct().getFproduct_credit_product_type_name(),
                        lists.get(i).getOptions_crdt_grad_avg()
                ));
            }
            return mainListResponseListDto;
        }
        return null;
    }
}
