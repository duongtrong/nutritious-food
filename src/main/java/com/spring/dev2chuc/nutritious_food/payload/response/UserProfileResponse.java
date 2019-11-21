package com.spring.dev2chuc.nutritious_food.payload.response;

import com.spring.dev2chuc.nutritious_food.model.UserProfile;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
    private Set<CategoryDTO> categories;


    public UserProfileResponse(UserProfile userProfile) {
        this.id = userProfile.getId();
        this.user_id = userProfile.getUser().getId();
        this.height = userProfile.getHeight();
        this.weight = userProfile.getWeight();
        this.age = LocalDate.now().getYear() - userProfile.getYearOfBirth();
        this.status = userProfile.getStatus();
        this.categories = userProfile.getCategories().stream().map(x -> new CategoryDTO(x)).collect(Collectors.toSet());
    }
}
