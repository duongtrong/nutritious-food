package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.*;
import com.spring.dev2chuc.nutritious_food.security.JwtTokenProvider;
import com.spring.dev2chuc.nutritious_food.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Optional<User> user = userService.findByUsernameOrPhone(loginRequest.getAccount(), loginRequest.getAccount());
        if (user.isPresent()) {
            User userCurrent = user.get();
            if (!passwordEncoder.matches(loginRequest.getPassword(), userCurrent.getPassword())) {
                return new ResponseEntity<>(
                        new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(),
                                "Password not matches"),
                        HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(
                    new ApiResponse(HttpStatus.UNAUTHORIZED.value(),
                            "Account notfound"),
                    HttpStatus.UNAUTHORIZED);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getAccount(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return new ResponseEntity<>(new JwtAuthenticationResponse(HttpStatus.OK.value(), "Login Success", jwt), HttpStatus.OK);
    }

    // signup for permission user

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByPhone(signUpRequest.getPhone())) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Phone Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        User current = new User();
        User result = userService.merge(current, signUpRequest);

        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "User registered successfully", result), HttpStatus.CREATED);
    }

    // private sign up for permission admin

    @PostMapping("/admin/signup")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByPhone(signUpRequest.getPhone())) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Phone Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        User current = new User();
        User result = userService.mergeAdmin(current, signUpRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Admin create successfully", result), HttpStatus.CREATED);
    }
}
