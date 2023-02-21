package com.example.creditmarket.service.mypage.impl;

import com.example.creditmarket.dto.FavoriteResponseDTO;
import com.example.creditmarket.dto.OrderResponseDTO;
import com.example.creditmarket.entity.EntityFavorite;
import com.example.creditmarket.entity.EntityOrder;
import com.example.creditmarket.entity.EntityUser;
import com.example.creditmarket.exception.AppException;
import com.example.creditmarket.exception.ErrorCode;
import com.example.creditmarket.repository.FavoriteRepository;
import com.example.creditmarket.repository.OrderRepository;
import com.example.creditmarket.repository.UserRepository;
import com.example.creditmarket.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;

    public List<FavoriteResponseDTO> selectFavoriteList(int page, String userEmail) {
        EntityUser user = userRepository.findById(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.USERMAIL_NOT_FOUND, userEmail + " 존재하지 않는 회원입니다."));

        if (page < 1) {
            throw new AppException(ErrorCode.PAGE_INDEX_ZERO, "Page가 1보다 작습니다.");
        }
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by("favoriteId").descending());

        List<EntityFavorite> favorites = favoriteRepository.findByUser(user, pageRequest);

        return favorites.stream()
                .map(FavoriteResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<OrderResponseDTO> selectOrderList(int page, String userEmail) {
        EntityUser user = userRepository.findById(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.USERMAIL_NOT_FOUND, userEmail + " 존재하지 않는 회원입니다."));

        if (page < 1) {
            throw new AppException(ErrorCode.PAGE_INDEX_ZERO, "Page가 1보다 작습니다.");
        }
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by("orderId").descending());

        List<EntityOrder> orders = orderRepository.findByUser(user, pageRequest);

        return orders.stream()
                .map(OrderResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public String updateOrder(Long orderId, String userEmail) {
        EntityUser user = userRepository.findById(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.USERMAIL_NOT_FOUND, userEmail + " 존재하지 않는 회원입니다."));

        EntityOrder order = orderRepository.findByUserAndOrderId(user, orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND, orderId + " 존재하지 않는 구매 상품입니다."));

        order.setOrderStatus(0);

        orderRepository.save(order);

        return "success";
    }
}
