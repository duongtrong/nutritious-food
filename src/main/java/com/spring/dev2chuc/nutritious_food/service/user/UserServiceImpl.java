package com.spring.dev2chuc.nutritious_food.service.user;

import com.spring.dev2chuc.nutritious_food.exception.AppException;
import com.spring.dev2chuc.nutritious_food.model.Role;
import com.spring.dev2chuc.nutritious_food.model.RoleName;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.SignUpRequest;
import com.spring.dev2chuc.nutritious_food.repository.RoleRepository;
import com.spring.dev2chuc.nutritious_food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

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
    public Optional<User> findByUsername(String username) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
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
    public boolean existsByUsername(String username) {
        SignUpRequest signUpRequest = new SignUpRequest();
        Boolean user = userRepository.existsByUsername(signUpRequest.getUsername());
        if (user == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean existsByPhone(String phone) {
        SignUpRequest signUpRequest = new SignUpRequest();
        Boolean user = userRepository.existsByPhone(signUpRequest.getPhone());
        if (user == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        SignUpRequest signUpRequest = new SignUpRequest();
        Boolean user = userRepository.existsByEmail(signUpRequest.getEmail());
        if (user == null) {
            return true;
        }
        return false;
    }
}