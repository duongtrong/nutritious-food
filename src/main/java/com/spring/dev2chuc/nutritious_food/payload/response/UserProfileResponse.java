package com.spring.dev2chuc.nutritious_food.payload.response;

import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.model.UserProfile;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserProfileResponse {
    private Long id;
    private Long user_id;
    private Integer height;
    private Integer weight;
    private Integer age;
    private Integer status;
    private Set<OnlyCategoryResponse> categories;


    public UserProfileResponse(UserProfile userProfile) {
        this.id = userProfile.getId();
        this.user_id = userProfile.getUser().getId();
        this.height = userProfile.getHeight();
        this.weight = userProfile.getWeight();
        this.age = userProfile.getAge();
        this.status = userProfile.getStatus();
        this.categories = userProfile.getCategories().stream().map(x -> new OnlyCategoryResponse(x)).collect(Collectors.toSet());
    }
}
