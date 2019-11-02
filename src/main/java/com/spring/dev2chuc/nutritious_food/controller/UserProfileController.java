package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.exception.AppException;
import com.spring.dev2chuc.nutritious_food.exception.ResourceNotFoundException;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.model.UserProfile;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.LoginRequest;
import com.spring.dev2chuc.nutritious_food.payload.UserProfileRequest;
import com.spring.dev2chuc.nutritious_food.repository.UserProfileRepository;
import com.spring.dev2chuc.nutritious_food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody UserProfileRequest userProfileRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            System.out.println(username + " == " + user.getId());

            if (user == null) {
                return new ResponseEntity(new ApiResponse(false, "User notfound"), HttpStatus.NOT_FOUND);
            }
            UserProfile userProfile = new UserProfile(user, userProfileRequest.getHeight(), userProfileRequest.getWeight(), userProfileRequest.getAge());
            UserProfile userProfileResult = userProfileRepository.save(userProfile);
            return new ResponseEntity(new ApiResponse(true, "ok", userProfileResult), HttpStatus.CREATED);
        }
        return new ResponseEntity(new ApiResponse(true, "You has reject"), HttpStatus.BAD_REQUEST);
    }
}
