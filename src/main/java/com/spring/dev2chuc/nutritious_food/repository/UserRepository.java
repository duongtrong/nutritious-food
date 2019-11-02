package com.spring.dev2chuc.nutritious_food.repository;

import com.spring.dev2chuc.nutritious_food.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrPhone(String username, String phone);

    Optional<User> findByPassword(String password);

    List<User> findByIdIn(List<Long> userIds);

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByPhone(String phone);

    Boolean existsByEmail(String email);
}
