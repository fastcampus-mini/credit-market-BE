package com.example.creditmarket.service.cart;

import com.example.creditmarket.dto.CartDeleteRequestDTO;
import com.example.creditmarket.dto.CartSaveRequestDTO;
import com.example.creditmarket.dto.CartResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CartService {

    String saveCart(CartSaveRequestDTO cartRequestDTO, Authentication authentication);

    List<CartResponseDTO> selectCartList(int page, Authentication authentication);

    String deleteCart(CartDeleteRequestDTO cartDeleteRequestDTO, Authentication authentication);
}
