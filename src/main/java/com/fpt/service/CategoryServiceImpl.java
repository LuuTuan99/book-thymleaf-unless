package com.fpt.service;

import com.fpt.entity.Category;
import com.fpt.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> search(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category getById(long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        category.setCreatedAtMLS(Calendar.getInstance().getTimeInMillis());
        category.setUpdatedAtMLS(Calendar.getInstance().getTimeInMillis());
        category.setStatus(Category.Status.ACTIVE.getValue());
        return categoryRepository.save(category);
    }

    @Override
    public Category update(long id, Category updateCategory) {
        Category existCategory = categoryRepository.findById(id).orElse(null);

        existCategory.setName(updateCategory.getName());
        existCategory.setThumbnail(updateCategory.getThumbnail());
        existCategory.setDescription(updateCategory.getDescription());
        return categoryRepository.save(updateCategory);
    }

    @Override
    public boolean delete(long id) {
        Category existCategory = categoryRepository.findById(id).orElse(null);
        if (existCategory == null) {
            return false;
        }
        categoryRepository.delete(existCategory);
        return false;
    }
}
