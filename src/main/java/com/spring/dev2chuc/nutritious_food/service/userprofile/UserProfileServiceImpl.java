package com.spring.dev2chuc.nutritious_food.service.userprofile;

import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.model.UserProfile;
import com.spring.dev2chuc.nutritious_food.payload.UserProfileRequest;
import com.spring.dev2chuc.nutritious_food.repository.UserProfileRepository;
import com.spring.dev2chuc.nutritious_food.service.category.CategoryService;
import com.spring.dev2chuc.nutritious_food.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class UserProfileServiceImpl implements UserProfileService{

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Override
    public List<UserProfile> getAllByUser(User user) {
        return userProfileRepository.findByUser(user);
    }

    @Override
    public UserProfile store(User user, UserProfileRequest userProfileRequest) {
        UserProfile userProfile = new UserProfile(
                user,
                userProfileRequest.getHeight(),
                userProfileRequest.getWeight(),
                userProfileRequest.getAge(),
                userProfileRequest.getBodyFat(),
                userProfileRequest.getExerciseIntensity(),
                userProfileRequest.getLbmIndex(),
                userProfileRequest.getBmrIndex(),
                userProfileRequest.getTdeeIndex(),
                userProfileRequest.getCaloriesConsumed(),
                userProfileRequest.getDesiredWeight(),
                userProfileRequest.getDietTime(),
                userProfileRequest.getCaloriesChange()
        );
        return userProfileRepository.save(userProfile);
    }

    @Override
    public Optional<UserProfile> getDetail(Long id) {
        if (CollectionUtils.isEmpty (Collections.singleton (id))) {
            return null;
//            throw new RuntimeException ("Null pointer exception");
        }
        System.out.println(id);
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        return userProfile;
    }

    @Override
    public UserProfile update(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }
}
