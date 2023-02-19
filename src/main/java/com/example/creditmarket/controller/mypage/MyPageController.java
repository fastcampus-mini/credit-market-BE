package com.example.creditmarket.controller.mypage;

import com.example.creditmarket.dto.FavoriteResponseDTO;
import com.example.creditmarket.dto.OrderResponseDTO;
import com.example.creditmarket.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    //토큰 받기전에 임시로 email 받기
    @GetMapping("/favor/{page}")
    public List<FavoriteResponseDTO> selectFavoriteList(@PathVariable int page, String userEmail) {
        return myPageService.selectFavoriteList(page, userEmail);
    }

    @GetMapping("/buy/{page}")
    public List<OrderResponseDTO> selectOrderList(@PathVariable int page, String userEmail) {
        return myPageService.selectOrderList(page, userEmail);
    }

}
