package com.example.creditmarket.service.cart.impl;

import com.example.creditmarket.dto.CartDeleteRequestDTO;
import com.example.creditmarket.dto.CartResponseDTO;
import com.example.creditmarket.dto.CartSaveRequestDTO;
import com.example.creditmarket.entity.EntityCart;
import com.example.creditmarket.entity.EntityFProduct;
import com.example.creditmarket.entity.EntityUser;
import com.example.creditmarket.repository.CartRepository;
import com.example.creditmarket.repository.FProductRespository;
import com.example.creditmarket.repository.FavoriteRepository;
import com.example.creditmarket.repository.UserRepository;
import com.example.creditmarket.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final FProductRespository fProductRespository;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;

    @Override
    public String saveCart(CartSaveRequestDTO cartRequestDTO, Authentication authentication) {
        EntityUser user = getUser(authentication);

        String fproductId = cartRequestDTO.getFproductId();
        EntityFProduct fProduct = fProductRespository.findById(fproductId)
                .orElseThrow(() -> new IllegalArgumentException("없는 상품입니다."));

        if (cartRepository.existsByUserAndFproduct(user, fProduct)) {
            return "failed"; //고민 해보기. 예외 던지기?
        }

        EntityCart cart = EntityCart.builder()
                .user(user)
                .fproduct(fProduct)
                .build();

        cartRepository.save(cart);

        return "success"; //상태코드 반환? ex)201
    }

    @Override
    public List<CartResponseDTO> selectCartList(int page, Authentication authentication) {
        EntityUser user = getUser(authentication);

        if (page < 1) { //예외 정하기
//            throw new Exception("page는 1 이상이여야 합니다.");
            return null;
        }
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by("cartId").descending());

        List<EntityCart> cartList = cartRepository.findByUser(user, pageRequest);

        return cartList.stream()
                .map(this::checkedFavorite)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteCart(CartDeleteRequestDTO cartDeleteRequestDTO, Authentication authentication) {
        EntityUser user = getUser(authentication);

        List<EntityCart> cartList = cartDeleteRequestDTO.toEntity();

        cartList.forEach(cart -> cartRepository.findByUserAndCartId(user, cart.getCartId())
                .orElseThrow(() -> new IllegalArgumentException("없는 상품입니다.")));

        cartRepository.deleteAll(cartList);

        return "success";
    }

    //장바구니에 관심 상품표시를 체크하는 메서드
    private CartResponseDTO checkedFavorite(EntityCart cart) {
        CartResponseDTO responseDTO = new CartResponseDTO(cart);
        if (favoriteRepository.existsByUserAndFproduct(cart.getUser(), cart.getFproduct())) {
            responseDTO.setFavorite(1);
        }
        return responseDTO;
    }

    //토큰에서 이메일을 꺼내고 회원인지 확인
    private EntityUser getUser(Authentication authentication) {
        String userEmail = authentication.getName();
        return userRepository.findById(userEmail) //예외처리 어떻게 할지
                .orElseThrow(() -> new IllegalArgumentException("없는 유저입니다."));
    }


}
