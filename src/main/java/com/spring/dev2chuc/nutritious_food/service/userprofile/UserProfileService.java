package com.spring.dev2chuc.nutritious_food.service.userprofile;

import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.model.UserProfile;
import com.spring.dev2chuc.nutritious_food.payload.UserProfileRequest;

import java.util.List;

public interface UserProfileService {

    List<UserProfile> getAllByUser(User user);

    UserProfile merge(UserProfile userProfile, UserProfileRequest userProfileRequest);

    UserProfile getDetail(Long id);

    UserProfile update(UserProfile userProfile, UserProfileRequest userProfileRequest);
}
