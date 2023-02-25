package com.example.creditmarket.repository;

import com.example.creditmarket.entity.EntityToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<EntityToken, String> {

    EntityToken findByToken(String UserToken);
}
