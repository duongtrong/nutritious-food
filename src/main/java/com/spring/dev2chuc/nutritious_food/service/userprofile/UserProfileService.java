package com.spring.dev2chuc.nutritious_food.service.userprofile;

import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.model.UserProfile;
import com.spring.dev2chuc.nutritious_food.payload.UserProfileRequest;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {

    List<UserProfile> getAllByUser(User user);

    UserProfile store(User user, UserProfileRequest userProfileRequest);

    Optional<UserProfile> getDetail(Long id);

    UserProfile update(UserProfile userProfile);
}
