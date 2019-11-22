package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.model.UserProfile;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseCustom;
import com.spring.dev2chuc.nutritious_food.payload.UserProfileRequest;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.payload.response.UserProfileDTO;
import com.spring.dev2chuc.nutritious_food.service.category.CategoryService;
import com.spring.dev2chuc.nutritious_food.service.user.UserService;
import com.spring.dev2chuc.nutritious_food.service.userprofile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Object> createUserProfile(@Valid @RequestBody UserProfileRequest userProfileRequest) {
        User user = userService.getUserAuth();
        if (user == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        } else {
            UserProfile profile = userProfileService.store(user, userProfileRequest);
            return new ResponseEntity<> (new ApiResponseCustom<> (HttpStatus.CREATED.value (), "Save order success", new UserProfileDTO(profile, false, false)), HttpStatus.CREATED);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @RequestBody UserProfileRequest userProfileRequest, @PathVariable("id") Long id) {
        User user = userService.getUserAuth();
        if (user == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        } else {
            UserProfile profile = userProfileService.getDetail(id);
            if (profile == null ) return new ResponseEntity<> (new ApiResponseCustom<> (HttpStatus.NOT_FOUND.value (), "Profile not found"), HttpStatus.NOT_FOUND);

            UserProfile result = userProfileService.update(userProfileRequest, profile);
            return new ResponseEntity<> (new ApiResponseCustom<> (HttpStatus.CREATED.value (), "Save order success", new UserProfileDTO(result, false, false)), HttpStatus.CREATED);
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        System.out.println(id);
        UserProfile userProfile = userProfileService.getDetail(id);
        if (userProfile == null) return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "Profile not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "Get detail success", new UserProfileDTO(userProfile, false, false)), HttpStatus.OK);
    }

    @PutMapping("/{id}/category")
    public ResponseEntity<?> updateCategory(@RequestBody List<Long> ids, @PathVariable("id") Long id) {
        UserProfile userProfile = userProfileService.getDetail(id);
        if (userProfile == null) {
            return new ResponseEntity<> (new ApiResponseError (HttpStatus.NOT_FOUND.value (), "Id not found"), HttpStatus.NOT_FOUND);
        }
        List<Category> categories = categoryService.findAllByIdIn(ids);
        Set<Category> categorySet = new HashSet<>(categories);
        userProfile.setCategories(categorySet);
        UserProfile profile = userProfileService.updateCategory(userProfile);
        return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "Create new category success", new UserProfileDTO(profile, false, false)), HttpStatus.OK);
    }
}
