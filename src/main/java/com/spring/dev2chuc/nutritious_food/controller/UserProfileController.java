package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.model.UserProfile;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.UserProfileRequest;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.service.category.CategoryService;
import com.spring.dev2chuc.nutritious_food.service.user.UserService;
import com.spring.dev2chuc.nutritious_food.service.userprofile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @PutMapping("/create")
    public ResponseEntity<Object> createUserProfile(@Valid @RequestBody UserProfileRequest userProfileRequest) {
        UserProfile userProfile = new UserProfile ();
        User user = userService.getUserAuth ();
        userProfile.setUser (user);

        if (user == null) {
            return new ResponseEntity<> (new ApiResponseError (HttpStatus.NOT_FOUND.value (), "User not found"), HttpStatus.NOT_FOUND);
        }

        UserProfile profile = userProfileService.merge (userProfile, userProfileRequest);
        return new ResponseEntity<> (new ApiResponse<> (HttpStatus.CREATED.value (), "Save order success", profile), HttpStatus.CREATED);

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            String username = authentication.getName();
//            User user = userService.findByUsername(username);
//            System.out.println(username + " == " + user.getId());
//
//            if (user == null) {
//                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
//            }
//            UserProfile userProfile = new UserProfile(user,
//                    userProfileRequest.getHeight(),
//                    userProfileRequest.getWeight(),
//                    userProfileRequest.getAge());
//            UserProfile userProfileResult = userProfileRepository.save(userProfile);
//            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Create success", userProfileResult), HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "You has reject"), HttpStatus.BAD_REQUEST);
//    }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        UserProfile userProfile = userProfileService.getDetail (id);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Get detail success", userProfile), HttpStatus.OK);
    }

    @PutMapping("/{id}/category")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody List<Long> ids, @RequestBody UserProfileRequest userProfileRequest, @PathVariable("id") Long id) {
        if (ids == null) {
            return new ResponseEntity<> (new ApiResponseError (HttpStatus.NOT_FOUND.value (), "Id not found"), HttpStatus.NOT_FOUND);
        }

        UserProfile userProfile = userProfileService.getDetail (id);
        if (userProfile == null) {
            return new ResponseEntity<> (new ApiResponseError (HttpStatus.NOT_FOUND.value (), "Id not found"), HttpStatus.NOT_FOUND);
        }

        UserProfile profile = userProfileService.update (userProfile, userProfileRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Create new category success", profile), HttpStatus.OK);
    }
}
