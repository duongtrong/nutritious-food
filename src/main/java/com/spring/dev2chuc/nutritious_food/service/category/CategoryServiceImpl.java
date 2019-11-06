package com.spring.dev2chuc.nutritious_food.service.category;

import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.model.Status;
import com.spring.dev2chuc.nutritious_food.payload.CategoryRequest;
import com.spring.dev2chuc.nutritious_food.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findByCategoriesByParentIdAndStatus(Long parentId, Integer status) {
        List<Category> list = categoryRepository.queryCategoriesByParentIdAndStatus(parentId, status);
        if (list == null) {
            return null;
        }
        return list;
    }

    @Override
    public List<Category> findAllByIdIn(Collection<Long> Ids) {
        List<Category> list = categoryRepository.findAllByIdIn(Ids);
        if (list == null) {
            return null;
        }
        return list;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllByStatusIs(Integer status) {
        List<Category> list = categoryRepository.findAllByStatusIs(status);
        if (list == null) {
            return null;
        }
        return list;
    }

    @Override
    public Category findByIdAndStatus(Long id, Integer status) {
        Category category = categoryRepository.findByIdAndStatus(id, status);
        if (category == null) {
            return null;
        }
        return category;
    }

    @Override
    public Category findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(null);
        return category;
    }

    @Override
    public Category merge(Category category, CategoryRequest categoryRequest) {
        category.setParentId(categoryRequest.getParentId());
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        category.setImage(categoryRequest.getImage());
        category.setStatus(Status.ACTIVE.getValue());
        return categoryRepository.save(category);
    }

    @Override
    public Category merge(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category, CategoryRequest categoryRequest) {
        if (categoryRequest.getName() != null) category.setName(categoryRequest.getName());
        if (categoryRequest.getImage() != null) category.setImage(categoryRequest.getImage());
        if (categoryRequest.getDescription() != null) category.setDescription(categoryRequest.getDescription());
        if (categoryRequest.getParentId() != null) category.setParentId(categoryRequest.getParentId());
        return categoryRepository.save(category);
    }
}
