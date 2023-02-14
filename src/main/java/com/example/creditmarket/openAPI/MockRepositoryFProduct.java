package com.example.creditmarket.openAPI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MockRepositoryFProduct extends JpaRepository<EntityFProduct, Long>{

}
