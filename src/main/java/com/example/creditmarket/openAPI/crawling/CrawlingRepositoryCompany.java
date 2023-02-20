package com.example.creditmarket.openAPI.crawling;


import com.example.creditmarket.entity.EntityFProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlingRepositoryCompany extends JpaRepository<EntityFProduct, String> {

}
