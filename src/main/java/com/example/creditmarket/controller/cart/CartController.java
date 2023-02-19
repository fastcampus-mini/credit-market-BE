package com.example.creditmarket.controller.cart;

import com.example.creditmarket.dto.CartDeleteRequestDTO;
import com.example.creditmarket.dto.CartSaveRequestDTO;
import com.example.creditmarket.dto.CartResponseDTO;
import com.example.creditmarket.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    //토큰 받기전에 임시로 email 받기
    @PostMapping
    public String saveCart(@RequestBody CartSaveRequestDTO cartRequestDTO, String userEmail) {
        return cartService.saveCart(cartRequestDTO, userEmail);
    }

    @GetMapping("/{page}")
    public List<CartResponseDTO> selectCartList(@PathVariable int page, String userEmail) {
        return cartService.selectCartList(page, userEmail);
    }

    @DeleteMapping
    public String deleteCart(CartDeleteRequestDTO cartDeleteRequestDTO, String userEmail) {
        return cartService.deleteCart(cartDeleteRequestDTO, userEmail);
    }
}
