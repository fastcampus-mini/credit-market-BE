package com.example.creditmarket.service.Impl;

import com.example.creditmarket.entity.EntityFProduct;
import com.example.creditmarket.entity.EntityOption;
import com.example.creditmarket.repository.FProductRespository;
import com.example.creditmarket.repository.OptionRepository;
import com.example.creditmarket.service.AutoCompleteService;
import com.example.creditmarket.utils.AutoCompleteUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutoCompleteServiceImpl implements AutoCompleteService {

    private final FProductRespository fProductRespository;
    private final OptionRepository optionRepository;
    private final AutoCompleteUtil root = new AutoCompleteUtil();

    public List<String> getAutoComplete(String prefix) {
        AutoCompleteUtil current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!current.children.containsKey(ch)) {
                return Collections.emptyList();
            }
            current = current.children.get(ch);
        }
        return current.getWordsWithPrefix(prefix);
    }

    @PostConstruct
    public void addKeywords() { // 금융회사명, 금융상품명, 대출종류명, 금리구분
        List<String> companyNames = fProductRespository.findAll().stream()
                .map(EntityFProduct::getFproduct_company_name)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
        for (String companyName : companyNames) {
            root.addWord(companyName);
        }
        List<String> fproductNames = fProductRespository.findAll().stream()
                .map(EntityFProduct::getFproduct_name)
                .filter(Objects::nonNull) // filter out null values
                .distinct() // remove duplicates
                .collect(Collectors.toList());
        for (String fproductName : fproductNames) {
            root.addWord(fproductName);
        }
        List<String> fproductCreditProductTypeNames = fProductRespository.findAll().stream()
                .map(EntityFProduct::getFproduct_credit_product_type_name)
                .filter(Objects::nonNull) // filter out null values
                .distinct() // remove duplicates
                .collect(Collectors.toList());
        for (String fproductCreditProductTypeName : fproductCreditProductTypeNames) {
            root.addWord(fproductCreditProductTypeName);
        }
        List<String> optionsInterestTypes = optionRepository.findAll().stream()
                .map(EntityOption::getOptions_interest_type)
                .filter(Objects::nonNull) // filter out null values
                .distinct() // remove duplicates
                .collect(Collectors.toList());
        for (String optionsInterestType : optionsInterestTypes) {
            root.addWord(optionsInterestType);
        }
    }
}

//    private final List<String> words = new ArrayList<String>(Arrays.asList(
////          금융회사명
//            "우리은행", "한국스탠다드차타드은행", "한국씨티은행", "대구은행",
//            "부산은행", "광주은행", "제주은행", "전북은행",
//            "경남은행", "중소기업은행", "한국산업은행", "현대카드㈜",
//            "롯데카드㈜", "비씨카드㈜", "삼성카드㈜", "신한카드㈜",
//            "오케이캐피탈 ㈜", "한국캐피탈㈜", "케이비캐피탈㈜", "우리금융캐피탈㈜",
//            "롯데캐피탈㈜", "제이비우리캐피탈㈜", "현대캐피탈㈜", "미래에셋캐피탈㈜",
//            "애큐온저축은행", "OSB저축은행", "디비저축은행", "민국저축은행",
//            "키움예스저축은행", "SBI저축은행", "고려저축은행", "모아저축은행",
//            "키움저축은행", "세람저축은행", "페퍼저축은행", "한화저축은행",
//            "청주저축은행", "한성저축은행", "상상인플러스저축은행", "스타저축은행",
//            "스마트저축은행", "한국투자저축은행", "동원제일저축은행", "진주저축은행",
//            "한화생명보험주식회사", "삼성생명보험주식회사", "흥국생명보험주식회사", "교보생명보험주식회사",
//            "신한라이프생명보험주식회사", "미래에셋생명보험주식회사", "DB손해보험주식회사", "국민은행",
//            "예가람저축은행", "신한은행", "JT저축은행", "엔에이치농협캐피탈㈜",
//            "삼호저축은행", "㈜디지비캐피탈", "비엔케이캐피탈㈜", "폭스바겐파이낸셜서비스코리아㈜",
//            "㈜케이비국민카드", "엔에이치저축은행", "대신저축은행", "BNK저축은행",
//            "KB저축은행", "하나저축은행", "농협생명보험주식회사", "농협은행주식회사",
//            "메리츠캐피탈㈜", "JT친애저축은행", "신한저축은행", "웰컴저축은행",
//            "OK저축은행", "㈜우리카드", "하나카드㈜", "하나은행",
//            "주식회사 케이뱅크", "수협은행", "주식회사 카카오뱅크", "토스뱅크 주식회사",
//
////          금융상품명
//            "협약금리 外 신용대출상품", "개인신용대출", "장기카드대출", "일반신용대출",
//            "마이너스한도대출", "ONE신용대출", "개인신용대출2", "가계일반자금대출",
//            "평생종합통장", "개인신용대출(신규대출취급중단)", "Easy 직장인 프로미론", "개인신용대출(일반신용대출)",
//            "개인신용대출(마이너스한도대출)", "가계신용대출(일반)", "가계신용대출(마이너스대출)", "신용대출",
//            "마이너스통장대출", "개인신용마이너스한도대출", "토스뱅크 신용대출", "토스뱅크 마이너스 통장대출",
//
////          대출종류명
//            "일반신용대출", "마이너스한도대출", "장기카드대출(카드론)", "신용대출",
//
////          금리 구분
//            "대출금리", "기준금리", "가산금리", "가감조정금리"
//    ));
//
//    public List<String> autocomplete(String substring) {
//        List<String> results = new ArrayList<>();
//        for (String s : words) {
//            if (s.contains(substring)) {
//                results.add(s);
//            }
//        }
//        return results;
//    }


