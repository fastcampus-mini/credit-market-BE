package com.example.creditmarket.repository;

import com.example.creditmarket.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<EntityUser, String> {

    Optional<EntityUser> findByUserEmail(String user_email);
}
