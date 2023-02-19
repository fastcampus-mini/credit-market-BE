package com.example.creditmarket.openAPI.service.impl;

import com.example.creditmarket.openAPI.dto.FavoriteRequestDto;
import com.example.creditmarket.openAPI.dto.ProductDetailDto;
import com.example.creditmarket.openAPI.entity.EntityFavorite;
import com.example.creditmarket.openAPI.entity.EntityOption;
import com.example.creditmarket.openAPI.entity.EntityUser;
import com.example.creditmarket.openAPI.dto.RecommendResponseDto;
import com.example.creditmarket.openAPI.repository.*;
import com.example.creditmarket.openAPI.entity.EntityFProduct;
import com.example.creditmarket.openAPI.dto.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OptionRepository optionRepository;
    private final FavoriteRepository favoriteRepository;

    /**
     * 상세 페이지
     */
    public ProductDetailDto getProductDetail(String id) {
        EntityFProduct product = productRepository.findByProductId(id);
        EntityOption option = optionRepository.findByProductId(id);
        return new ProductDetailDto(product, option);
    }

    /**
     * 상품 구매
     */
    public String buyProduct(String productId, String userEmail) {
        EntityFProduct result = productRepository.findById(productId).orElseThrow(() ->
                new IllegalArgumentException("해당 상품을 찾을수 없습니다"));
        EntityUser user = userRepository.findById(userEmail).orElseThrow(() ->
                new IllegalArgumentException("해당 회원을 찾을수 없습니다."));
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setUser(user);
        orderRequestDto.setFProduct(result);
        if (orderRepository.save(orderRequestDto.toEntity()) != null) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 추천 게시글
     */
    public List<RecommendResponseDto> recommendList() {
        //수정 필요
        // 로그인 여부 authentication으로 판단해서 if 문으로 감싸기
        String userEmail = "myemail@"; //임시
        List<RecommendResponseDto> list = new ArrayList<>();
        EntityUser user = userRepository.findById(userEmail).orElseThrow();
        List<EntityFProduct> products = productRepository.findProductsByUserPref(user.getUser_pref_credit_product_type_name());
        for (EntityFProduct pr : products) {
            List<EntityOption> options = optionRepository.findOptionByProductIdAndType(pr.getFproduct_id(), user.getUser_pref_interest_type());
            for (EntityOption op : options) {
                RecommendResponseDto recommendResponseDto = new RecommendResponseDto(pr, op);
                list.add(recommendResponseDto);}}
        return list;
    }

    /**
     * 찜 추가
     */
    public String favoriteService(String productId, String userEmail) {
        // authentication 추가되면 수정 필요
        EntityFProduct product = productRepository.findById(productId).orElseThrow(() ->
                new IllegalArgumentException("해당 상품을 찾을수 없습니다"));
        EntityUser user = userRepository.findById(userEmail).orElseThrow(() ->
                new IllegalArgumentException("해당 회원을 찾을수 없습니다."));
        try{
            EntityFavorite favorite = favoriteRepository.findByFProductAndUser(productId, userEmail);
        if (favorite == null) {
            FavoriteRequestDto dto = new FavoriteRequestDto();
            favoriteRepository.save(dto.toEntity(user, product));
        } else {
            favoriteRepository.deleteById(favorite.getFavoriteId());
        }
            return "success";
        } catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
}
