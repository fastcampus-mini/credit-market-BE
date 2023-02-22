package com.example.creditmarket.service;

import com.example.creditmarket.dto.request.CartDeleteRequestDTO;
import com.example.creditmarket.dto.request.CartSaveRequestDTO;
import com.example.creditmarket.dto.response.CartResponseDTO;

import java.util.List;

public interface CartService {

    String saveCart(CartSaveRequestDTO cartRequestDTO, String userEmail);

    List<CartResponseDTO> selectCartList(String userEmail);

    String deleteCart(CartDeleteRequestDTO cartDeleteRequestDTO, String userEmail);
}
