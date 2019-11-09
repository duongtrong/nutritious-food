package com.spring.dev2chuc.nutritious_food.service.user;

import com.spring.dev2chuc.nutritious_food.model.RoleName;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.SignUpRequest;
import com.spring.dev2chuc.nutritious_food.payload.response.OnlyUserResponse;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);

    User findByUsername(String username);

    Optional<User> findByUsernameOrPhone(String username, String phone);

    User merge(User user, SignUpRequest signUpRequest);

    User mergeAdmin(User user, SignUpRequest signUpRequest);

    List<OnlyUserResponse> findAllByRoles(RoleName name);

    boolean existsByUsername(String username);

    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);
}
