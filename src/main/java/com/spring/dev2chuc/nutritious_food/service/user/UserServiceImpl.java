package com.spring.dev2chuc.nutritious_food.service.user;

import com.spring.dev2chuc.nutritious_food.exception.AppException;
import com.spring.dev2chuc.nutritious_food.model.Role;
import com.spring.dev2chuc.nutritious_food.model.RoleName;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.SignUpRequest;
import com.spring.dev2chuc.nutritious_food.payload.response.OnlyUserResponse;
import com.spring.dev2chuc.nutritious_food.repository.RoleRepository;
import com.spring.dev2chuc.nutritious_food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl<T> implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public Optional<User> findByUsernameOrPhone(String username, String phone) {
        Optional<User> user = userRepository.findByUsernameOrPhone(username, phone);
        return user;
    }

    @Override
    public User merge(User user, SignUpRequest signUpRequest) {
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setPhone(signUpRequest.getPhone());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("Role user not set."));

        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user);
    }

    @Override
    public User mergeAdmin(User user, SignUpRequest signUpRequest) {
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setPhone(signUpRequest.getPhone());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new AppException("Role admin not set."));

        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user);
    }

    @Override
    public List<OnlyUserResponse> findAllByRoles(RoleName name) {
        Optional<Role> demo = roleRepository.findByName(name);
        List<User> list = userRepository.findAllByRoles(name);
        return list.stream().map(x -> new OnlyUserResponse(x)).collect(Collectors.toList());
    }


    @Override
    public boolean existsByUsername(String username) {
        if (username == null || username.trim().length() == 0) {
            return true;
        }
        SignUpRequest signUpRequest = new SignUpRequest();
        Boolean user = userRepository.existsByUsername(signUpRequest.getUsername());
        if (user == null) {
            throw new RuntimeException("Null pointer exception");
        }
        return false;
    }

    @Override
    public boolean existsByPhone(String phone) {
        if (phone == null || phone.trim().length() == 0) {
            return true;
        }
        SignUpRequest signUpRequest = new SignUpRequest();
        Boolean user = userRepository.existsByPhone(signUpRequest.getPhone());
        if (user == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        if (email == null || email.trim().length() == 0) {
            return true;
        }
        SignUpRequest signUpRequest = new SignUpRequest();
        Boolean user = userRepository.existsByEmail(signUpRequest.getEmail());
        if (user == null) {
            throw new IllegalArgumentException();
        }
        return false;
    }
}
