package com.example.creditmarket.openAPI.crawling;


import com.example.creditmarket.entity.EntityOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlingRepositoryFProduct extends JpaRepository<EntityOption, Long> {

}
