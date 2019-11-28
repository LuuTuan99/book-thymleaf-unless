package com.fpt.service.admin;

import com.fpt.entity.Author;
import com.fpt.entity.Book;
import com.fpt.entity.Category;
import com.fpt.repository.CategoryRepository;
import com.fpt.service.admin.CategoryService;
import com.fpt.specification.BookSpecification;
import com.fpt.specification.CategorySpecification;
import com.fpt.specification.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Page<Category> findAllActive(Specification specification, Pageable pageable) {
        specification = specification
                .and(new CategorySpecification(new SearchCriteria("status", "!=", Category.Status.DELETED.getValue())));
        return categoryRepository.findAll(specification, pageable);
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
        return categoryRepository.save(existCategory);
    }

    @Override
    public boolean delete(long id) {
        Category existCategory = categoryRepository.findById(id).orElse(null);
        if (existCategory == null) {
            return false;
        }
        existCategory.setDeletedAtMLS(Calendar.getInstance().getTimeInMillis());
        existCategory.setStatus(Book.Status.DELETED.getValue());
        categoryRepository.save(existCategory);
        return false;
    }
}
