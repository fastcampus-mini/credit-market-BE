package com.example.creditmarket.service.product;

import com.example.creditmarket.dto.ProductDetailDto;
import com.example.creditmarket.dto.RecommendResponseDto;

import java.util.List;

public interface ProductService {

    ProductDetailDto getProductDetail(String productId);

    String buyProduct(String productId,String userEmail);

    List<RecommendResponseDto> recommendList(String userEmail);

    String favoriteService(String productId, String userEmail);
}
