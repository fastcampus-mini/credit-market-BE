package com.example.creditmarket.controller;

import com.example.creditmarket.dto.ProductDetailDto;
import com.example.creditmarket.dto.RecommendResponseDto;
import com.example.creditmarket.dto.UserLoginRequestDTO;
import com.example.creditmarket.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/item/{id}")
    public ProductDetailDto itemDetail(@PathVariable String id){
        return productService.getProductDetail(id);
    }

    @PostMapping("/order/{id}")
    public String buyProduct(@PathVariable String productId, Authentication authentication){
        UserLoginRequestDTO user = (UserLoginRequestDTO) authentication.getPrincipal();
        return productService.buyProduct(productId, user);
    }

    @GetMapping("/recommend")
    public List<RecommendResponseDto> recommendList(Authentication authentication){
        UserLoginRequestDTO user = (UserLoginRequestDTO) authentication.getPrincipal();
        return productService.recommendList(user);
    }

    @PostMapping("/favor/{id}")
    public String addLike(@PathVariable String id, Authentication authentication){
        UserLoginRequestDTO user = (UserLoginRequestDTO) authentication.getPrincipal();
        return productService.favoriteService(id, user);
    }

    @DeleteMapping ("/favor/{id}")
    public String cancelLike(@PathVariable String id, Authentication authentication){
        UserLoginRequestDTO user = (UserLoginRequestDTO) authentication.getPrincipal();
        return productService.favoriteService(id, user);
    }

}
