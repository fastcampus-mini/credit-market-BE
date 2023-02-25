package com.example.creditmarket.repository;

import com.example.creditmarket.entity.EntityOrder;
import com.example.creditmarket.entity.EntityUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<EntityOrder, Long> {

    List<EntityOrder> findByUser(EntityUser user, Pageable pageable);

    Optional<EntityOrder> findByUserAndOrderId(EntityUser user, Long orderId);
}
