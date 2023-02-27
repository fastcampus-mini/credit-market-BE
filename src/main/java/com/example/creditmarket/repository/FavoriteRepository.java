package com.example.creditmarket.repository;

import com.example.creditmarket.entity.EntityFProduct;
import com.example.creditmarket.entity.EntityFavorite;
import com.example.creditmarket.entity.EntityUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<EntityFavorite, Long> {

    List<EntityFavorite> findByUser(EntityUser user, Pageable pageable);

    boolean existsByUserAndFproduct(EntityUser user, EntityFProduct fproduct);

    EntityFavorite findEntityFavoriteByFproductAndUser(EntityFProduct fproduct, EntityUser user);

    int countByUser(EntityUser user);

}

