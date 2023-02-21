package com.example.creditmarket.controller;

import com.example.creditmarket.dto.ProductDetailDto;
import com.example.creditmarket.dto.RecommendResponseDto;
import com.example.creditmarket.dto.UserLoginRequestDTO;
import com.example.creditmarket.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(tags = {"상품 서비스"}, description = "상품 상세페이지, 주문, 추천 상품, 상품 찜하기 기능을 담당합니다.")
public class ProductController {

    private final ProductService productService;

    @ApiOperation(value = "상품 상세페이지", notes = "상품의 상세페이지를 보여줍니다.")
    @GetMapping("/item/{id}")
    public ProductDetailDto itemDetail(@PathVariable String id){
        return productService.getProductDetail(id);
    }

    @ApiOperation(value = "상품 구매", notes = "상품을 구매(신청)합니다.")
    @PostMapping("/order/{id}")
    public String buyProduct(@PathVariable String id, Authentication authentication){
        String userEmail = authentication.getName();
        return productService.buyProduct(id, userEmail);
    }

    @ApiOperation(value = "상품 추천", notes = "회원의 선호하는 대출상품과 금리에 따라 상품을 추천해줍니다")
    @GetMapping("/recommend")
    public List<RecommendResponseDto> recommendList(Authentication authentication){
        String userEmail = authentication.getName();
        return productService.recommendList(userEmail);
    }

    @ApiOperation(value = "찜하기", notes = "상품을 찜합니다.")
    @PostMapping("/favor/{id}")
    public String addLike(@PathVariable String id, Authentication authentication){
        String userEmail = authentication.getName();
        return productService.favoriteService(id, userEmail);
    }

    @ApiOperation(value = "찜 취소", notes = "상품의 찜을 취소합니다")
    @DeleteMapping ("/favor/{id}")
    public String cancelLike(@PathVariable String id, Authentication authentication){
        String userEmail = authentication.getName();
        return productService.favoriteService(id, userEmail);
    }

}
