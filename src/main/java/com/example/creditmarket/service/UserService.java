package com.example.creditmarket.service;

import com.example.creditmarket.dto.UserSignUpRequest;
import com.example.creditmarket.entity.EntityUser;
import com.example.creditmarket.exception.AppException;
import com.example.creditmarket.exception.ErrorCode;
import com.example.creditmarket.repository.UserRepository;
import com.example.creditmarket.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secretKey}")
    private String secretKey;
    private Long expiredMs = 1000 * 60 * 60 * 24 * 7L ; //일주일

    public String signup(UserSignUpRequest request) {

        //userEmail 중복 체크
        userRepository.findByUserEmail(request.getUserEmail())
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, "이미 존재하는 이메일입니다.");
                });

        //저장
        //userRepository.save(request.toEntity());
        EntityUser EncodedEntityUser = EntityUser.builder()
                .userEmail(request.getUserEmail())
                .userPassword(encoder.encode(request.getUserPassword())) //Encoded
                .userGender(request.getUserGender())
                .userBirthdate(request.getUserBirthDate())
                .userJob(request.getUserJob())
                .userPrefCreditProductTypeName(request.getUserPrefCreditProductTypeName())
                .userPrefInterestType(request.getUserPrefInterestType())
                .userCreditScore(request.getUserCreditScore())
                .build();
        userRepository.save(EncodedEntityUser);
        System.out.println(EncodedEntityUser.toString());
        return "success";
    }

    public String login(String userEmail, String password){
        //userEmail 없음
        EntityUser selectedUser = userRepository.findByUserEmail(userEmail)
                .orElseThrow(()->new AppException(ErrorCode.USERNAME_NOT_FOUND, userEmail + " 존재하지 않는 회원입니다."));

        //password 틀림
        if (!encoder.matches(password, selectedUser.getUserPassword())) { //순서 중요. inputpassword, DBpassword
            throw new AppException(ErrorCode.INVALID_PASSWORD, "비밀번호가 틀렸습니다.");
        }

        String token = JwtUtil.createToken(selectedUser.getUserEmail(), secretKey, expiredMs);
        return token;
    }
}
