package com.example.creditmarket.repository;

import com.example.creditmarket.entity.EntityCart;
import com.example.creditmarket.entity.EntityFProduct;
import com.example.creditmarket.entity.EntityUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<EntityCart, Long> {

    List<EntityCart> findByUser(EntityUser user, Pageable pageable);

    Optional<EntityCart> findByUserAndCartId(EntityUser user, Long cartId);

    boolean existsByUserAndFproduct(EntityUser user, EntityFProduct fProduct);
}
