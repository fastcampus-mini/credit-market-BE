package com.example.creditmarket.controller;

import com.example.creditmarket.dto.CartDeleteRequestDTO;
import com.example.creditmarket.dto.CartResponseDTO;
import com.example.creditmarket.dto.CartSaveRequestDTO;
import com.example.creditmarket.service.cart.CartService;
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

    //토큰 받기전에 임시로 email 받기
    @PostMapping
    public ResponseEntity<String> saveCart(@RequestBody CartSaveRequestDTO cartRequestDTO, Authentication authentication) {
        String result = cartService.saveCart(cartRequestDTO, authentication);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{page}")
    public ResponseEntity<List<CartResponseDTO>> selectCartList(@PathVariable int page, Authentication authentication) {
        List<CartResponseDTO> cartList = cartService.selectCartList(page, authentication);

        return ResponseEntity.ok().body(cartList);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCart(@RequestBody CartDeleteRequestDTO cartDeleteRequestDTO, Authentication authentication) {
        String result = cartService.deleteCart(cartDeleteRequestDTO, authentication);

        return ResponseEntity.ok().body(result);
    }
}
