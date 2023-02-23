package com.example.creditmarket.service.Impl;

import com.example.creditmarket.dto.request.FavoriteRequestDto;
import com.example.creditmarket.dto.request.OrderRequestDTO;
import com.example.creditmarket.dto.request.OrderSaveRequestDTO;
import com.example.creditmarket.dto.response.ProductDetailResponseDTO;
import com.example.creditmarket.dto.response.RecommendResponseDTO;
import com.example.creditmarket.entity.EntityFProduct;
import com.example.creditmarket.entity.EntityFavorite;
import com.example.creditmarket.entity.EntityOption;
import com.example.creditmarket.entity.EntityUser;
import com.example.creditmarket.repository.*;
import com.example.creditmarket.service.ProductService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final FProductRespository productRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OptionRepository optionRepository;
    private final FavoriteRepository favoriteRepository;

    @Value("${jwt.token.secret}")
    private String secretKey;

    /**
     * 상품 상세정보 출력(상품명, 개요, 대상, 한도, 금리, 찜 여부 등의 상세정보 출력)
     */
    public ProductDetailResponseDTO getProductDetail(String id, HttpServletRequest request) {
        EntityFProduct product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디를 찾을수 없습니다"));
        EntityOption option = optionRepository.findByProductId(id);
        if (getEmailFromToken(request) == null) {
            return new ProductDetailResponseDTO(product, option, false);
        }
        String userEmail = getEmailFromToken(request);
        EntityUser user = userRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디를 찾을수 없습니다."));
        boolean isFavorite = favoriteRepository.existsByUserAndFproduct(user, product);
        return new ProductDetailResponseDTO(product, option, isFavorite);
    }

    /**
     * 상품 구매
     */
    public String buyProduct(OrderSaveRequestDTO requestDTO, String userEmail) {
        EntityUser user = userRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디를 찾을수 없습니다."));

        List<String> productIds = requestDTO.getProductIds();
        List<EntityFProduct> products = productIds.stream()
                .map(productId -> productRepository.findById(productId).orElseThrow(() ->
                        new IllegalArgumentException("해당 상품을 찾을수 없습니다")))
                .collect(Collectors.toList());

        OrderRequestDTO orderRequestDto = new OrderRequestDTO();
        orderRequestDto.setUser(user);
        for (EntityFProduct product : products) {
            orderRequestDto.setFproduct(product);
            orderRepository.save(orderRequestDto.toEntity());
        }

        return "success";
    }

    /**
     * 추천 게시글
     */
    public List<RecommendResponseDTO> recommendList(HttpServletRequest request) {
        List<RecommendResponseDTO> list = new ArrayList<>();
        if (getEmailFromToken(request) == null) {
            return null;
        }
        String userEmail = getEmailFromToken(request);
        EntityUser user = userRepository.findById(userEmail).orElseThrow(() -> new IllegalArgumentException("해당 아이디를 찾을수 없습니다."));
        List<EntityFProduct> products = productRepository.findProductsByUserPref(user.getUserPrefCreditProductTypeName());
        for (EntityFProduct pr : products) {
            EntityOption op = optionRepository.findOptionByProductIdAndType(pr.getFproduct_id(), user.getUserPrefInterestType());
            if (op != null) {
                list.add(new RecommendResponseDTO(pr, op));
            }
        }
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
        try {
            EntityFavorite favorite = favoriteRepository.findEntityFavoriteByFproductAndUser(product, user);
            if (favorite == null) {
                FavoriteRequestDto dto = new FavoriteRequestDto();
                favoriteRepository.save(dto.toEntity(user, product));
            } else {
                favoriteRepository.deleteById(favorite.getFavoriteId());
            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    public String getEmailFromToken(HttpServletRequest request) {
        try {
            String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            String token = authorization.split(" ")[1].trim();
            ;
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                    .getBody().get("userEmail", String.class);
        } catch (Exception e) {
            return null;
        }
    }
}
