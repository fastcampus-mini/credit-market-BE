package com.example.creditmarket.service;

import com.example.creditmarket.dto.response.MainListResponseDTO;

import java.util.List;

public interface SearchService {

    public List<MainListResponseDTO> searchResult(String keyword, String loan, String age,
                                                  String gender, String interest, Double rate,
                                                  String userId, int page);


}
