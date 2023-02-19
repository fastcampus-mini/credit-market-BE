package com.example.creditmarket.service.mypage;

import com.example.creditmarket.dto.FavoriteResponseDTO;

import java.util.List;

public interface MyPageService {

    List<FavoriteResponseDTO> selectFavoriteList(int page, String userEmail);

}
