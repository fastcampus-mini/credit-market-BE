package com.example.creditmarket.service.cart;

import com.example.creditmarket.dto.CartDeleteRequestDTO;
import com.example.creditmarket.dto.CartSaveRequestDTO;
import com.example.creditmarket.dto.CartResponseDTO;

import java.util.List;

public interface CartService {

    String saveCart(CartSaveRequestDTO cartRequestDTO, String userEmail);

    List<CartResponseDTO> selectCartList(int page, String userEmail);

    String deleteCart(CartDeleteRequestDTO cartDeleteRequestDTO, String userEmail);
}
