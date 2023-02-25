package com.example.creditmarket.openAPI;

import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CrawlingOpenAPI {

    private final CrawlingService crawlingService;

    @GetMapping("/api")
    public String callAPI() throws IOException, ParseException {
        return crawlingService.callAllAPI();
    }
}
