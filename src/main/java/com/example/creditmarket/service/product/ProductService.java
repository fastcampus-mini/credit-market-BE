package com.example.creditmarket.service.product;

import com.example.creditmarket.dto.ProductDetailDto;
import com.example.creditmarket.dto.RecommendResponseDto;
import com.example.creditmarket.dto.UserLoginRequestDTO;

import java.util.List;

public interface ProductService {

    public ProductDetailDto getProductDetail(String productId);

    public String buyProduct(String productId,UserLoginRequestDTO loginDto);

    public List<RecommendResponseDto> recommendList(UserLoginRequestDTO loginDto);

    public String favoriteService(String productId, UserLoginRequestDTO loginDto);
}
