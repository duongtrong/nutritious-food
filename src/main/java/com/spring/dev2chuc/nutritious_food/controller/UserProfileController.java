package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.model.UserProfile;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.UserProfileRequest;
import com.spring.dev2chuc.nutritious_food.payload.response.UserProfileResponse;
import com.spring.dev2chuc.nutritious_food.repository.UserProfileRepository;
import com.spring.dev2chuc.nutritious_food.service.category.CategoryService;
import com.spring.dev2chuc.nutritious_food.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileController {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @PutMapping("/update")
    public ResponseEntity<Object> update(@Valid @RequestBody UserProfileRequest userProfileRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            User user = userService.findByUsername(username);
            System.out.println(username + " == " + user.getId());

            if (user == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
            }
            UserProfile userProfile = new UserProfile(user, userProfileRequest.getHeight(), userProfileRequest.getWeight(), userProfileRequest.getAge());
            UserProfile userProfileResult = userProfileRepository.save(userProfile);

            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Create success", new UserProfileResponse(userProfileResult)), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "You has reject"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        UserProfile userProfile = userProfileRepository.findById(id).orElseThrow(null);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Get detail success", userProfile), HttpStatus.OK);
    }

    @PutMapping("/{id}/category")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody List<Long> ids, @PathVariable("id") Long id) {
        System.out.println(ids);
        UserProfile userProfile = userProfileRepository.findById(id).orElseThrow(null);
        List<Category> categories = categoryService.findAllByIdIn(ids);
        System.out.println(categories.size());
        Set<Category> categorySet = new HashSet<>(categories);
        userProfile.setCategories(categorySet);
        userProfileRepository.save(userProfile);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Create new category success", userProfile), HttpStatus.OK);
    }
}
