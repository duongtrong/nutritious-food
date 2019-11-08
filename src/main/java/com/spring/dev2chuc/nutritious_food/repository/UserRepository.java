package com.spring.dev2chuc.nutritious_food.repository;

import com.spring.dev2chuc.nutritious_food.model.RoleName;
import com.spring.dev2chuc.nutritious_food.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrPhone(String username, String phone);

    List<User> findByIdIn(List<Long> userIds);

    @Query(value = "SELECT *\n" +
            "    FROM users\n" +
            "    INNER JOIN user_roles ON users.id = user_roles.user_id\n" +
            "    INNER JOIN roles ON user_roles.role_id = roles.id\n" +
            "    WHERE roles.name = \"ROLE_USER\"", nativeQuery = true)
    List<User> findAllByRoles(RoleName name);

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByPhone(String phone);

    Boolean existsByEmail(String email);
}
