package com.example.creditmarket.openAPI.repository;

import com.example.creditmarket.openAPI.entity.EntityFProduct;
import com.example.creditmarket.openAPI.entity.EntityFavorite;
import com.example.creditmarket.openAPI.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface FavoriteRepository extends JpaRepository<EntityFavorite, Long> {

    @Query(value = "select * from tb_favorite where fproduct_id = :productId and user_email = :userEmail", nativeQuery = true)
    EntityFavorite findByFProductAndUser(@Param("productId") String productId, @Param("userEmail") String userEmail);
}