package com.fpt.service.admin;

import com.fpt.entity.Author;
import com.fpt.pagination.PageModel;
import com.fpt.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PageModel pageModel;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public List<Author> search(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public Author getById(long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author save(Author author) {
        author.setCreatedAtMLS(Calendar.getInstance().getTimeInMillis());
        author.setUpdatedAtMLS(Calendar.getInstance().getTimeInMillis());
        author.setStatus(Author.Status.ACTIVE.getValue());
        return authorRepository.save(author);
    }

    @Override
    public Author update(long id, Author updateAuthor) {
        Author author = authorRepository.findById(id).orElse(null);

        author.setName(updateAuthor.getName());
        author.setAvatar(updateAuthor.getAvatar());
        author.setDescription(updateAuthor.getDescription());
        return authorRepository.save(updateAuthor);
    }

    @Override
    public boolean delete(long id) {
        Author existAuthor = authorRepository.findById(id).orElse(null);
        if (existAuthor == null) {
            return false;
        }
        authorRepository.delete(existAuthor);
        return true;
    }

}
