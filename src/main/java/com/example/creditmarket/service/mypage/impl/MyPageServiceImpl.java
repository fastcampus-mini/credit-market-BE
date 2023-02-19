package com.example.creditmarket.service.mypage.impl;

import com.example.creditmarket.dto.FavoriteResponseDTO;
import com.example.creditmarket.entity.EntityFavorite;
import com.example.creditmarket.entity.EntityUser;
import com.example.creditmarket.repository.FavoriteRepository;
import com.example.creditmarket.repository.UserRepository;
import com.example.creditmarket.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;

    //나중에 토큰 받아서 이메일얻는 메서드 따로 만들기
    public List<FavoriteResponseDTO> selectFavoriteList(int page, String userEmail) {
        EntityUser user = userRepository.findById(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("없는 유저입니다."));

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

}
