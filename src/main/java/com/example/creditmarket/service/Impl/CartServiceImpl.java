package com.example.creditmarket.service.Impl;

import com.example.creditmarket.dto.request.CartDeleteRequestDTO;
import com.example.creditmarket.dto.response.CartResponseDTO;
import com.example.creditmarket.dto.request.CartSaveRequestDTO;
import com.example.creditmarket.entity.EntityCart;
import com.example.creditmarket.entity.EntityFProduct;
import com.example.creditmarket.entity.EntityUser;
import com.example.creditmarket.exception.AppException;
import com.example.creditmarket.exception.ErrorCode;
import com.example.creditmarket.repository.CartRepository;
import com.example.creditmarket.repository.FProductRespository;
import com.example.creditmarket.repository.FavoriteRepository;
import com.example.creditmarket.repository.UserRepository;
import com.example.creditmarket.service.CartService;
import lombok.RequiredArgsConstructor;
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
    public String saveCart(CartSaveRequestDTO cartRequestDTO, String userEmail) {
        EntityUser user = userRepository.findById(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.USERMAIL_NOT_FOUND, userEmail + " 존재하지 않는 회원입니다."));

        String fproductId = cartRequestDTO.getFproductId();
        EntityFProduct fProduct = fProductRespository.findById(fproductId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND, fproductId + " 존재하지 않는 상품입니다."));

        EntityCart cart = EntityCart.builder()
                .user(user)
                .fproduct(fProduct)
                .build();

        cartRepository.save(cart);

        return "success";
    }

    @Override
    public List<CartResponseDTO> selectCartList(int page, String userEmail) {
        EntityUser user = userRepository.findById(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.USERMAIL_NOT_FOUND, userEmail + " 존재하지 않는 회원입니다."));

        List<EntityCart> cartList = cartRepository.findByUserOrderByCartIdDesc(user);

        return cartList.stream()
                .map(this::checkedFavorite)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteCart(CartDeleteRequestDTO cartDeleteRequestDTO, String userEmail) {
        EntityUser user = userRepository.findById(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.USERMAIL_NOT_FOUND, userEmail + " 존재하지 않는 회원입니다."));

        List<EntityCart> cartList = cartDeleteRequestDTO.toEntity();

        cartList.forEach(cart -> cartRepository.findByUserAndCartId(user, cart.getCartId())
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND, cart.getCartId() + " 존재하지 않는 장바구니 상품입니다.")));

        cartRepository.deleteAll(cartList);

        return "success";
    }

    //장바구니에 관심 상품표시를 체크하는 메서드
    private CartResponseDTO checkedFavorite(EntityCart cart) {
        CartResponseDTO responseDTO = new CartResponseDTO(cart);
        if (favoriteRepository.existsByUserAndFproduct(cart.getUser(), cart.getFproduct())) {
            responseDTO.setFavorite(true);
        }
        return responseDTO;
    }
}
