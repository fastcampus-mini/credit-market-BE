package com.example.creditmarket.controller;

import com.example.creditmarket.dto.CartDeleteRequestDTO;
import com.example.creditmarket.dto.CartResponseDTO;
import com.example.creditmarket.dto.CartSaveRequestDTO;
import com.example.creditmarket.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    //토큰 받기전에 임시로 email 받기
    @PostMapping
    public String saveCart(@RequestBody CartSaveRequestDTO cartRequestDTO, Authentication authentication) {
        return cartService.saveCart(cartRequestDTO, authentication);
    }

    @GetMapping("/{page}")
    public List<CartResponseDTO> selectCartList(@PathVariable int page, Authentication authentication) {
        return cartService.selectCartList(page, authentication);
    }

    @DeleteMapping
    public String deleteCart(@RequestBody CartDeleteRequestDTO cartDeleteRequestDTO, Authentication authentication) {
        return cartService.deleteCart(cartDeleteRequestDTO, authentication);
    }
}
