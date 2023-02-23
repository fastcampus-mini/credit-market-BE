package com.example.creditmarket.service;

import com.example.creditmarket.dto.response.ProductDetailResponseDTO;
import com.example.creditmarket.dto.response.RecommendResponseDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProductService {

    ProductDetailResponseDTO getProductDetail(String productId, HttpServletRequest request);

    String buyProduct(String productId,String userEmail);

    List<RecommendResponseDTO> recommendList(HttpServletRequest request);

    String favoriteService(String productId, String userEmail);
}
