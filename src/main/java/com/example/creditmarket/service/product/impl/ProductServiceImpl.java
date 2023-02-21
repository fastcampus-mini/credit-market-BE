package com.example.creditmarket.service.product.impl;

import com.example.creditmarket.dto.*;
import com.example.creditmarket.entity.EntityFProduct;
import com.example.creditmarket.entity.EntityFavorite;
import com.example.creditmarket.entity.EntityOption;
import com.example.creditmarket.entity.EntityUser;
import com.example.creditmarket.repository.*;
import com.example.creditmarket.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OptionRepository optionRepository;
    private final FavoriteRepository favoriteRepository;

    /**
     * 상품 상세정보 출력(상품명, 개요, 대상, 한도, 금리 등의 상세정보 출력)
     */
    public ProductDetailDto getProductDetail(String id) {
        EntityFProduct product = productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 아이디를 찾을수 없습니다"));
        EntityOption option = optionRepository.findByProductId(id);
        return new ProductDetailDto(product, option);
    }

    /**
     * 상품 구매
     */
    public String buyProduct(String productId, String userEmail) {
        EntityUser user = userRepository.findByUserEmail(userEmail).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디를 찾을수 없습니다."));
        EntityFProduct product = productRepository.findById(productId).orElseThrow(() ->
                new IllegalArgumentException("해당 상품을 찾을수 없습니다"));
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setUser(user);
        orderRequestDto.setFproduct(product);
        if (orderRepository.save(orderRequestDto.toEntity()) != null) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 추천 게시글
     */
    public List<RecommendResponseDto> recommendList(String userEmail) {
        List<RecommendResponseDto> list = new ArrayList<>();
        EntityUser user = userRepository.findById(userEmail).orElseThrow();
        List<EntityFProduct> products = productRepository.findProductsByUserPref(user.getUserPrefCreditProductTypeName());
        for (EntityFProduct pr : products) {
            List<EntityOption> options = optionRepository.findOptionByProductIdAndType(pr.getFproduct_id(), user.getUserPrefInterestType());
            for (EntityOption op : options) {
                list.add(new RecommendResponseDto(pr, op));}}
        return list;
    }

    /**
     * 찜 추가 & 취소
     */
    public String favoriteService(String productId, String userEmail) {
        EntityFProduct product = productRepository.findById(productId).orElseThrow(() ->
                new IllegalArgumentException("해당 상품을 찾을수 없습니다"));
        EntityUser user = userRepository.findById(userEmail).orElseThrow(() ->
                new IllegalArgumentException("해당 회원을 찾을수 없습니다."));
        try{
            EntityFavorite favorite = favoriteRepository.findEntityFavoriteByFproductAndUser(product, user);
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
