package com.fpt.service;

import com.fpt.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    List<Author> search(String name);

    Author getById(long id);

    Author save(Author author);

    Author update(long id, Author updateAuthor);

    boolean delete(long id);
}
