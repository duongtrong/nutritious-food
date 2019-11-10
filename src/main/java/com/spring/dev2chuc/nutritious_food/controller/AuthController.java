package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.model.UserProfile;
import com.spring.dev2chuc.nutritious_food.payload.LoginRequest;
import com.spring.dev2chuc.nutritious_food.payload.SignUpRequest;
import com.spring.dev2chuc.nutritious_food.payload.response.*;
import com.spring.dev2chuc.nutritious_food.security.JwtTokenProvider;
import com.spring.dev2chuc.nutritious_food.service.user.UserService;
import com.spring.dev2chuc.nutritious_food.service.userProfile.UserProfileService;
import org.hibernate.engine.internal.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

//    private static final String EMAIL_PATTERN
//            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

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
        if (signUpRequest.getName() == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Please enter your name."),
                    HttpStatus.BAD_REQUEST);
        }

        if (signUpRequest.getUsername() == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Please enter your username"),
                    HttpStatus.BAD_REQUEST);
        } else if (signUpRequest.getUsername().length() < 4) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Username is too short"),
                    HttpStatus.BAD_REQUEST);
        } else if (signUpRequest.getUsername().length() > 20) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Username is too long"),
                    HttpStatus.BAD_REQUEST);
        } else if (userService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (signUpRequest.getPhone() == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Please enter your phone."),
                    HttpStatus.BAD_REQUEST);
        } else if (signUpRequest.getPhone().length() < 10) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Phone number is in the wrong format"),
                    HttpStatus.BAD_REQUEST);
        } else if (userService.existsByPhone(signUpRequest.getPhone())) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Phone number already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (signUpRequest.getEmail() == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Please enter your email."),
                    HttpStatus.BAD_REQUEST);
        } else if (userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Email already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (signUpRequest.getPassword() == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Please enter your password."),
                    HttpStatus.BAD_REQUEST);
        } else if (signUpRequest.getPassword().length() <= 6) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Password is too short."),
                    HttpStatus.BAD_REQUEST);
        } else if (signUpRequest.getPassword().length() >= 20) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Password is too long."),
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
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByPhone(signUpRequest.getPhone())) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Phone Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        User current = new User();
        User result = userService.mergeAdmin(current, signUpRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Admin create successfully", result), HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        User user = userService.getUserAuth();
        if (user == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Save order success", new OnlyUserResponse(user)), HttpStatus.OK);
        }
    }

    @GetMapping("/me-profile")
    public ResponseEntity<?> getUserProfile() {
        User user = userService.getUserAuth();

        if (user == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        } else {
            List<UserProfile> userProfiles = userProfileService.getAllByUser(user);
            List<UserProfileResponse> userProfileResponses = userProfiles.stream().map(x -> new UserProfileResponse(x)).collect(Collectors.toList());;
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "success", userProfileResponses), HttpStatus.OK);
        }
    }
}
