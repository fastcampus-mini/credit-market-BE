package com.example.creditmarket.controller;

import com.example.creditmarket.dto.FavoriteResponseDTO;
import com.example.creditmarket.dto.OrderResponseDTO;
import com.example.creditmarket.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
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
    public List<FavoriteResponseDTO> selectFavoriteList(@PathVariable int page, Authentication authentication) {
        return myPageService.selectFavoriteList(page, authentication);
    }

    @GetMapping("/buy/{page}")
    public List<OrderResponseDTO> selectOrderList(@PathVariable int page, Authentication authentication) {
        return myPageService.selectOrderList(page, authentication);
    }

    @PatchMapping("/buy/{orderId}")
    public String updateOrder(@PathVariable Long orderId, Authentication authentication) {
        return myPageService.updateOrder(orderId, authentication);
    }
}
