package com.spring.dev2chuc.nutritious_food.service.userProfile;

import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.model.UserProfile;
import com.spring.dev2chuc.nutritious_food.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public List<UserProfile> getAllByUser(User user) {
        return userProfileRepository.findByUser(user);
    }
}
