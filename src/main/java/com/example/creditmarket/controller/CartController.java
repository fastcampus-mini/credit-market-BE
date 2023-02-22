package com.example.creditmarket.controller;

import com.example.creditmarket.dto.request.CartDeleteRequestDTO;
import com.example.creditmarket.dto.request.CartSaveRequestDTO;
import com.example.creditmarket.dto.response.CartResponseDTO;
import com.example.creditmarket.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> saveCart(@RequestBody CartSaveRequestDTO cartRequestDTO, Authentication authentication) {
        String result = cartService.saveCart(cartRequestDTO, authentication.getName());

        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<List<CartResponseDTO>> selectCartList(Authentication authentication) {
        List<CartResponseDTO> cartList = cartService.selectCartList(authentication.getName());

        return ResponseEntity.ok().body(cartList);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCart(@RequestBody CartDeleteRequestDTO cartDeleteRequestDTO, Authentication authentication) {
        String result = cartService.deleteCart(cartDeleteRequestDTO, authentication.getName());

        return ResponseEntity.ok().body(result);
    }
}
