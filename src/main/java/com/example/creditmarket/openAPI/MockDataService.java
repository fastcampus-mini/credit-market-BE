package com.example.creditmarket.openAPI;


import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MockDataService {

    private final MockRepositoryCompany mockRepositoryCompany;
    private final MockRepositoryFProduct mockRepositoryFProduct;

    private final String creditLoanProductsSearch = "creditLoanProductsSearch"; //개인신용대출


    @Value("${open.api_key}")
    private String accessKey;
    private final String topFinGrpNo = "020000"; // 권역코드(은행)
    private final String pageNo = "1"; // 페이지 번호

    public String callAPI() throws IOException, ParseException {
        StringBuilder result = new StringBuilder();
        String urlStr = "http://finlife.fss.or.kr/finlifeapi/"+
                creditLoanProductsSearch+
                ".json?" +
                "auth=" + accessKey +
                "&topFinGrpNo=" + topFinGrpNo +
                "&pageNo=" + pageNo;
        URL url = new URL(urlStr);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
        String returnLine;
        while ((returnLine = br.readLine()) != null) {
            System.out.println(returnLine);
            result.append(returnLine + "\n\r");
        }
        urlConnection.disconnect();
        parsingJson(result.toString());
        saveAPICompany(result.toString());
        saveAPIFProduct(result.toString());
        return result.toString();
    }

    public void saveAPICompany(String json) throws ParseException{
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
        JSONObject jsonResult = (JSONObject) jsonObject.get("result");
        JSONArray jsonBaseList = (JSONArray) jsonResult.get("baseList");

        for (int i = 0; i < jsonBaseList.size(); i++) {
            JSONObject bl = (JSONObject) jsonBaseList.get(i);
            EntityCompany company = EntityCompany.builder()
                    .conoprdtnm(bl.get("fin_co_no").toString() + bl.get("crdt_prdt_type").toString())
                    .dcls_month(bl.get("dcls_month").toString())
                    .fin_co_no(bl.get("fin_co_no").toString())
                    .fin_prdt_cd(bl.get("fin_prdt_cd").toString())
                    .crdt_prdt_type(bl.get("crdt_prdt_type").toString())
                    .kor_co_nm(bl.get("kor_co_nm").toString())
                    .fin_prdt_nm(bl.get("fin_prdt_nm").toString())
                    .join_way(bl.get("join_way").toString())
                    .cb_name(bl.get("cb_name").toString())
                    .crdt_prdt_type_nm(bl.get("crdt_prdt_type_nm").toString())
                    .dcls_strt_day(bl.get("dcls_strt_day").toString())
                    .fin_co_subm_day(bl.get("fin_co_subm_day").toString())
                    .build();
            mockRepositoryCompany.save(company);
        }
    }

    public void saveAPIFProduct(String json) throws ParseException{
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
        JSONObject jsonResult = (JSONObject) jsonObject.get("result");
        JSONArray jsonOptionList = (JSONArray) jsonResult.get("optionList");
        try{
            for(int j=0; j<jsonOptionList.size(); j++){
                JSONObject ol = (JSONObject) jsonOptionList.get(j);
                Optional<EntityCompany> company = mockRepositoryCompany.findById(ol.get("fin_co_no").toString() + ol.get("crdt_prdt_type").toString());
                EntityFProduct product = EntityFProduct.builder()
                        .entityCompany(company.get())
                        .crdt_lend_rate_type(ol.get("crdt_lend_rate_type").toString())
                        .crdt_lend_rate_type_nm(ol.get("crdt_lend_rate_type_nm").toString())
                        .crdt_grad_1(ol.get("crdt_grad_1") == null ? 0 : Double.valueOf(ol.get("crdt_grad_1").toString()))
                        .crdt_grad_4(ol.get("crdt_grad_4") == null ? 0 : Double.valueOf(ol.get("crdt_grad_4").toString()))
                        .crdt_grad_5(ol.get("crdt_grad_5") == null ? 0 : Double.valueOf(ol.get("crdt_grad_5").toString()))
                        .crdt_grad_6(ol.get("crdt_grad_6") == null ? 0 : Double.valueOf(ol.get("crdt_grad_6").toString()))
                        .crdt_grad_10(ol.get("crdt_grad_10") == null ? 0 : Double.valueOf(ol.get("crdt_grad_10").toString()))
                        .crdt_grad_11(ol.get("crdt_grad_11") == null ? 0 : Double.valueOf(ol.get("crdt_grad_11").toString()))
                        .crdt_grad_12(ol.get("crdt_grad_12") == null ? 0 : Double.valueOf(ol.get("crdt_grad_12").toString()))
                        .crdt_grad_13(ol.get("crdt_grad_13") == null ? 0 : Double.valueOf(ol.get("crdt_grad_13").toString()))
                        .crdt_grad_avg(ol.get("crdt_grad_avg") == null ? 0 : Double.valueOf(ol.get("crdt_grad_avg").toString()))
                        .build();
                mockRepositoryFProduct.save(product);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void parsingJson(String json) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
        JSONObject jsonResult = (JSONObject) jsonObject.get("result");
        JSONArray jsonBaseList = (JSONArray) jsonResult.get("baseList");
        JSONArray jsonOptionList = (JSONArray) jsonResult.get("optionList");

        for (int i = 0; i < jsonBaseList.size(); i++) {
//            for (int i = 0; i < 2; i++) {
            JSONObject bl = (JSONObject) jsonBaseList.get(i);
            System.out.println("공시 제출월: " + bl.get("dcls_month"));
            System.out.println("금융회사코드: " + bl.get("fin_co_no"));
            System.out.println("금융회사 명: " + bl.get("fin_prdt_cd"));
            System.out.println("금융상품코드: " + bl.get("crdt_prdt_type"));
            System.out.println("금융상품명: " + bl.get("kor_co_nm")); //
            System.out.println("가입방법: " + bl.get("fin_prdt_nm"));
            System.out.println("대출 종류 코드: " + bl.get("join_way"));
            System.out.println("대출종류명: " + bl.get("cb_name"));
            System.out.println("CB 회사명: " + bl.get("crdt_prdt_type_nm"));
            System.out.println("공시 시작일: " + bl.get("dcls_strt_day"));
            System.out.println("공시 종료일: " + bl.get("dcls_end_day"));
            System.out.println("금융회사 제출일[YYYYMMDDHH24MI]: " + bl.get("fin_co_subm_day"));

            String blCode = bl.get("fin_co_no").toString() + bl.get("crdt_prdt_type").toString();
            for(int j=0; j<jsonOptionList.size(); j++){
                String olCode = ((JSONObject) jsonOptionList.get(j)).get("fin_co_no").toString() + ((JSONObject) jsonOptionList.get(j)).get("crdt_prdt_type").toString();
                if (blCode.equals(olCode)){
                    System.out.println("----------------------------------------");
                    JSONObject ol = (JSONObject) jsonOptionList.get(j);
                    System.out.println("공시 제출월: " + ol.get("dcls_month"));
                    System.out.println("금융회사코드: " + ol.get("fin_co_no"));
                    System.out.println("금융회사 명: " + ol.get("fin_prdt_cd"));
                    System.out.println("금융상품코드: " + ol.get("crdt_prdt_type"));

                    System.out.println("금리구분 코드: " + ol.get("crdt_lend_rate_type"));
                    System.out.println("금리구분: " + ol.get("crdt_lend_rate_type_nm"));
                    System.out.println("900점 초과: " + ol.get("crdt_grad_1"));
                    System.out.println("801~900점: " + ol.get("crdt_grad_4"));
                    System.out.println("701~800점: " + ol.get("crdt_grad_5"));
                    System.out.println("601~700점: " + ol.get("crdt_grad_6"));
                    System.out.println("501~600점: " + ol.get("crdt_grad_10"));
                    System.out.println("401~500점: " + ol.get("crdt_grad_11"));
                    System.out.println("301~500점: " + ol.get("crdt_grad_12"));
                    System.out.println("300점 이하: " + ol.get("crdt_grad_13"));
                    System.out.println("평균 금리: " + ol.get("crdt_grad_avg"));
                }
            }
        }
    }
}
