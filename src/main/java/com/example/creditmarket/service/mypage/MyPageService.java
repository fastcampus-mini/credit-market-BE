package com.example.creditmarket.service.mypage;

import com.example.creditmarket.dto.FavoriteResponseDTO;
import com.example.creditmarket.dto.OrderResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface MyPageService {

    List<FavoriteResponseDTO> selectFavoriteList(int page, Authentication authentication);

    List<OrderResponseDTO> selectOrderList(int page, Authentication authentication);

    String updateOrder(Long orderId, Authentication authentication);

}
