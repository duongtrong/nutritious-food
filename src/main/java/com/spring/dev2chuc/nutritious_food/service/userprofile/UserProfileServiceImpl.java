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

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public UserProfile merge(UserProfile userProfile, UserProfileRequest userProfileRequest) {
        userProfile.setHeight (userProfileRequest.getHeight ());
        userProfile.setWeight (userProfileRequest.getWeight ());
        userProfile.setAge (userProfileRequest.getAge ());
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile getDetail(Long id) {
        if (CollectionUtils.isEmpty (Collections.singleton (id))) {
            throw new RuntimeException ("Null pointer exception");
        }
        return userProfileRepository.findById (id).orElseThrow (null);
    }

    @Override
    public UserProfile update(UserProfile userProfile, UserProfileRequest userProfileRequest) {
        List<Category> categories = categoryService.findAllByIdIn(userProfileRequest.getCateId ());
        if (CollectionUtils.isEmpty (categories)) {
            throw new RuntimeException ("Null pointer exception");
        }
        Set<Category> categorySet = new HashSet<>(categories);
        userProfile.setCategories(categorySet);
        return userProfile;
    }
}
