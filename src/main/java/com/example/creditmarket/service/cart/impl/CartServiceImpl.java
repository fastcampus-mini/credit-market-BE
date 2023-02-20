package com.example.creditmarket.service.cart.impl;

import com.example.creditmarket.dto.CartDeleteRequestDTO;
import com.example.creditmarket.dto.CartResponseDTO;
import com.example.creditmarket.dto.CartSaveRequestDTO;
import com.example.creditmarket.entity.EntityCart;
import com.example.creditmarket.entity.EntityFProduct;
import com.example.creditmarket.entity.EntityUser;
import com.example.creditmarket.exception.AppException;
import com.example.creditmarket.exception.ErrorCode;
import com.example.creditmarket.repository.CartRepository;
import com.example.creditmarket.repository.FProductRespository;
import com.example.creditmarket.repository.FavoriteRepository;
import com.example.creditmarket.repository.UserRepository;
import com.example.creditmarket.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND, fproductId + "존재하지 않는 상품입니다."));

        EntityCart cart = EntityCart.builder()
                .user(user)
                .fproduct(fProduct)
                .build();

        cartRepository.save(cart);

        return "success";
    }

    @Override
    public List<CartResponseDTO> selectCartList(int page, Authentication authentication) {
        EntityUser user = getUser(authentication);

        if (page < 1) {
            throw new AppException(ErrorCode.PAGE_INDEX_ZERO, "Page가 1보다 작습니다.");
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
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND, cart.getCartId() + "존재하지 않는 장바구니 상품입니다.")));

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
                .orElseThrow(() -> new AppException(ErrorCode.USERMAIL_NOT_FOUND, userEmail + " 존재하지 않는 회원입니다."));
    }
}
