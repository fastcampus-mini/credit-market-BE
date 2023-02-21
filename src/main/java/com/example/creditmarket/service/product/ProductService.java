package com.example.creditmarket.service.product;

import com.example.creditmarket.dto.response.ProductDetailResponseDTO;
import com.example.creditmarket.dto.response.RecommendResponseDTO;

import java.util.List;

public interface ProductService {

    ProductDetailResponseDTO getProductDetail(String productId);

    String buyProduct(String productId,String userEmail);

    List<RecommendResponseDTO> recommendList(String userEmail);

    String favoriteService(String productId, String userEmail);
}
