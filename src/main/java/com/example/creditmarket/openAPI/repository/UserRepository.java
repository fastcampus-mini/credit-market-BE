package com.example.creditmarket.openAPI.repository;

import com.example.creditmarket.openAPI.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<EntityUser, String> {
}