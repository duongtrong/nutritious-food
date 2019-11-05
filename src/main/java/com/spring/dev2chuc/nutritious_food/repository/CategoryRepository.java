package com.spring.dev2chuc.nutritious_food.repository;

import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> queryCategoriesByParentIdAndStatus(Long parentId, Integer status);

    List<Category> findAllByIdIn(Collection<Long> Ids);

    List<Category> findAllByStatusIs(Integer status);

    Category findByIdAndStatus(Long id, Integer status);
}
