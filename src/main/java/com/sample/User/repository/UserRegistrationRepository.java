package com.sample.User.repository;


import com.sample.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRegistrationRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

     boolean existsByEmail(String email);

}
