package com.example.creditmarket.controller;

import com.example.creditmarket.dto.response.MainListResponseDTO;
import com.example.creditmarket.service.AutoCompleteService;
import com.example.creditmarket.service.Impl.AutoCompleteServiceImpl;
import com.example.creditmarket.service.Impl.SearchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import io.swagger.annotations.Api;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = {"검색 서비스"}, description = "키워드와 카테고리 검색을 담당합니다.")
public class SearchController {

    private final SearchServiceImpl searchServiceImpl;

    private final AutoCompleteService autoCompleteService;

    @GetMapping("/search/results")
    public List<MainListResponseDTO> searchKeyword(@RequestParam(required = false, defaultValue = "") @Nullable String keyword,
                                                   @RequestParam(required = false, defaultValue = "") @Nullable String loan,
                                                   @RequestParam(required = false, defaultValue = "") @Nullable String age,
                                                   @RequestParam(required = false, defaultValue = "") @Nullable String gender,
                                                   @RequestParam(required = false, defaultValue = "대출금리") @Nullable String interest,
                                                   @RequestParam(required = false, defaultValue = "0") @Nullable Double rate,
                                                   Authentication authentication,
                                                   @RequestParam(required = false, defaultValue = "1") int page
                                                ) {
        String userEmail = "";
        if(authentication == null){
            return searchServiceImpl.searchResult(keyword.trim(), loan.trim(), age.trim(), gender.trim(), interest.trim(), rate, userEmail, (page-1));
        }
        userEmail = authentication.getPrincipal().toString();
        return searchServiceImpl.searchResult(keyword.trim(), loan.trim(), age.trim(), gender.trim(), interest.trim(), rate, userEmail, (page-1));
    }

    @PostMapping("/search/autocomplete")
    public List<String> getAutoComplete(@RequestBody @Nullable String prefix) {
        return autoCompleteService.getAutoComplete(prefix);
    }
}
