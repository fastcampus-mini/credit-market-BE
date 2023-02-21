package com.example.creditmarket.service.cart;

import com.example.creditmarket.dto.request.CartDeleteRequestDTO;
import com.example.creditmarket.dto.request.CartSaveRequestDTO;
import com.example.creditmarket.dto.response.CartResponseDTO;

import java.util.List;

public interface CartService {

    String saveCart(CartSaveRequestDTO cartRequestDTO, String userEmail);

    List<CartResponseDTO> selectCartList(int page, String userEmail);

    String deleteCart(CartDeleteRequestDTO cartDeleteRequestDTO, String userEmail);
}
