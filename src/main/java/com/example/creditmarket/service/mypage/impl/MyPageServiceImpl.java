package com.example.creditmarket.service.mypage.impl;

import com.example.creditmarket.dto.FavoriteResponseDTO;
import com.example.creditmarket.dto.OrderResponseDTO;
import com.example.creditmarket.entity.EntityFavorite;
import com.example.creditmarket.entity.EntityOrder;
import com.example.creditmarket.entity.EntityUser;
import com.example.creditmarket.repository.FavoriteRepository;
import com.example.creditmarket.repository.OrderRepository;
import com.example.creditmarket.repository.UserRepository;
import com.example.creditmarket.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;

    //나중에 토큰 받아서 이메일얻는 메서드 따로 만들기
    public List<FavoriteResponseDTO> selectFavoriteList(int page, Authentication authentication) {
        EntityUser user = getUser(authentication);

        if (page < 1) { //예외 정하기
//            throw new Exception("page는 1 이상이여야 합니다.");
            return null;
        }
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by("favoriteId").descending());

        List<EntityFavorite> favorites = favoriteRepository.findByUser(user, pageRequest);

        return favorites.stream()
                .map(FavoriteResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<OrderResponseDTO> selectOrderList(int page, Authentication authentication) {
        EntityUser user = getUser(authentication);

        if (page < 1) { //예외 정하기
//            throw new Exception("page는 1 이상이여야 합니다.");
            return null;
        }
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by("orderId").descending());

        List<EntityOrder> orders = orderRepository.findByUser(user, pageRequest);

        return orders.stream()
                .map(this::checkedFavorite)
                .collect(Collectors.toList());
    }

    //상품 취소
    @Override
    public String updateOrder(Long orderId, Authentication authentication) {
        EntityUser user = getUser(authentication);

        EntityOrder order = orderRepository.findByUserAndOrderId(user, orderId)
                .orElseThrow(() -> new IllegalArgumentException("없는 상품입니다."));

        order.setOrderStatus(0L);

        orderRepository.save(order);

        return "success";
    }

    //구매 상품목록에 관심 상품표시를 체크하는 메서드
    private OrderResponseDTO checkedFavorite(EntityOrder order) {
        OrderResponseDTO responseDTO = new OrderResponseDTO(order);
        if (favoriteRepository.existsByUserAndFproduct(order.getUser(), order.getFproduct())) {
            responseDTO.setFavorite(1);
        }
        return responseDTO;
    }

    //토큰에서 이메일을 꺼내고 회원인지 확인
    private EntityUser getUser(Authentication authentication) {
        String userEmail = authentication.getName();
        return userRepository.findById(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("없는 유저입니다."));
    }
}
