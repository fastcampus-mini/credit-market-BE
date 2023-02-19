package com.example.creditmarket.openAPI.controller;

import com.example.creditmarket.openAPI.dto.ProductDetailDto;
import com.example.creditmarket.openAPI.dto.RecommendResponseDto;
import com.example.creditmarket.openAPI.service.impl.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    /**
     * 상품 상세정보 출력(상품명, 개요, 대상, 한도, 금리 등의 상세정보 출력)
     */
    @GetMapping("/item/{id}")
    public ProductDetailDto itemDetail(@PathVariable String id){
        return productService.getProductDetail(id);
    }

    @PostMapping("/order/{id}")
    public String requestProduct(@PathVariable String productId, String userId){
        return productService.buyProduct(productId, userId);
    }

    @GetMapping("/recommend")
    public List<RecommendResponseDto> recommendList(){
        return productService.recommendList();
    }

    @PostMapping("/favor/{id}")
    public String addLike(@PathVariable String id){
        String userEmail = "myemail@";
        return productService.favoriteService(id, userEmail);
    }

    @DeleteMapping ("/favor/{id}")
    public String cancelLike(@PathVariable String id){
        String userEmail = "myemail@";
        return productService.favoriteService(id, userEmail);
    }

}
