package com.example.creditmarket.service;

import com.example.creditmarket.dto.response.FavoriteResponseDTO;
import com.example.creditmarket.dto.response.OrderResponseDTO;

import java.util.List;

public interface MyPageService {

    List<FavoriteResponseDTO> selectFavoriteList(int page, String userEmail);

    List<OrderResponseDTO> selectOrderList(int page, String userEmail);

    String updateOrder(Long orderId, String userEmail);

}
