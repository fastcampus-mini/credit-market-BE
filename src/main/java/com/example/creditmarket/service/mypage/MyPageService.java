package com.example.creditmarket.service.mypage;

import com.example.creditmarket.dto.FavoriteResponseDTO;
import com.example.creditmarket.dto.OrderResponseDTO;

import java.util.List;

public interface MyPageService {

    List<FavoriteResponseDTO> selectFavoriteList(int page, String userEmail);

    List<OrderResponseDTO> selectOrderList(int page, String userEmail);

    String updateOrder(Long orderId, String userEmail);

}
