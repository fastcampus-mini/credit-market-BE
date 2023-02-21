package com.example.creditmarket.controller;

import com.example.creditmarket.dto.response.FavoriteResponseDTO;
import com.example.creditmarket.dto.response.OrderResponseDTO;
import com.example.creditmarket.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/favor/{page}")
    public ResponseEntity<List<FavoriteResponseDTO>> selectFavoriteList(@PathVariable int page, Authentication authentication) {
        List<FavoriteResponseDTO> favoriteList = myPageService.selectFavoriteList(page, authentication.getName());

        return ResponseEntity.ok().body(favoriteList);
    }

    @GetMapping("/buy/{page}")
    public ResponseEntity<List<OrderResponseDTO>> selectOrderList(@PathVariable int page, Authentication authentication) {
        List<OrderResponseDTO> orderList = myPageService.selectOrderList(page, authentication.getName());

        return ResponseEntity.ok().body(orderList);
    }

    @PatchMapping("/buy/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable Long orderId, Authentication authentication) {
        String result = myPageService.updateOrder(orderId, authentication.getName());

        return ResponseEntity.ok().body(result);
    }
}
