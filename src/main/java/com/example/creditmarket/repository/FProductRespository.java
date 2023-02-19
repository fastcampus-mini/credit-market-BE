package com.example.creditmarket.repository;

import com.example.creditmarket.entity.EntityFProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FProductRespository extends JpaRepository<EntityFProduct, String> {


}
