package com.example.creditmarket.openAPI.crawling;

import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CrawlingOpenAPI {

    private final CrawlingService crawlingService;

    private String accessKey = "47254f11d1939203ddadb54b7f65442f"; //인증키
    private String topFinGrpNo = "020000"; // 권역코드(은행)
    private String pageNo = "1"; // 페이지 번호

    @GetMapping("/api")
    public String callAPI() throws IOException, ParseException {
        return crawlingService.callAllAPI();
    }
}
