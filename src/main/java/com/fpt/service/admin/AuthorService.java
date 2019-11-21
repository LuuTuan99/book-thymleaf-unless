package com.fpt.service.admin;

import com.fpt.entity.Author;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    Page<Author> getAll();

    List<Author> search(String name);

    Author getById(long id);

    Author save(Author author);

    Author update(long id, Author updateAuthor);

    boolean delete(long id);
}
