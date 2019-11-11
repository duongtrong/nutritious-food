package com.spring.dev2chuc.nutritious_food.service.userProfile;

import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserProfileService {
    List<UserProfile> getAllByUser(User user);
}
