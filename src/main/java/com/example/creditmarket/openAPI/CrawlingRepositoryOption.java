package com.example.creditmarket.openAPI;


import com.example.creditmarket.entity.EntityOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlingRepositoryOption extends JpaRepository<EntityOption, Long> {

}
