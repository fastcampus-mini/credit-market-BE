package com.example.creditmarket.repository;

import com.example.creditmarket.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<EntityUser, String> {

}
