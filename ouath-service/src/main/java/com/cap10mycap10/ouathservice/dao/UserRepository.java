package com.cap10mycap10.ouathservice.dao;

import com.cap10mycap10.ouathservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String name);


}
