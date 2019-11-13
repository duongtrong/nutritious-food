package com.spring.dev2chuc.nutritious_food.repository;

import com.spring.dev2chuc.nutritious_food.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    List<Category> queryCategoriesByParentIdAndStatus(Long parentId, Integer status);

    List<Category> findAllByIdIn(Collection<Long> Ids);

    List<Category> findAllByStatusIs(Integer status);

    Category findByIdAndStatus(Long id, Integer status);
}
