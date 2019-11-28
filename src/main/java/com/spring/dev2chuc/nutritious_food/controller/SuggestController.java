package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Combo;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.model.UserProfile;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseCustom;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.payload.response.ComboDTO;
import com.spring.dev2chuc.nutritious_food.service.combo.ComboService;
import com.spring.dev2chuc.nutritious_food.service.user.UserService;
import com.spring.dev2chuc.nutritious_food.service.userprofile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/suggest")
public class SuggestController {

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    UserService userService;

    @Autowired
    ComboService comboService;

    @GetMapping("/combo")
    public ResponseEntity<Object> suggestCombo() {
        User user = userService.getUserAuth();
        if (user == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        } else {
            UserProfile userProfile = userProfileService.getLatestByUser(user);
            List<Combo> current = comboService.suggest(userProfile);
            return new ResponseEntity<> (new ApiResponseCustom<>(HttpStatus.OK.value (), "Get List user profile success", current.stream().map(x -> new ComboDTO(x, true, true)).collect(Collectors.toList())), HttpStatus.OK);
        }
    }
}
