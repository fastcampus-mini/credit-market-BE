package com.example.creditmarket.openAPI.repository;

import com.example.creditmarket.openAPI.entity.EntityOrder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<EntityOrder, Long> {
}