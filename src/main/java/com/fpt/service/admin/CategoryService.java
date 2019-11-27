package com.fpt.service.admin;

import com.fpt.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    List<Category> search(String name);

    Category getById(long id);

    Category save(Category category);

    Category update(long id, Category updateCategory);

    boolean delete(long id);
}
