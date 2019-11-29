package com.fpt.service.admin;

import com.fpt.entity.Book;
import com.fpt.repository.BookRepository;
import com.fpt.specification.BookSpecification;
import com.fpt.specification.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Page<Book> findAllActive(Specification specification, Pageable pageable) {
        specification = specification
                .and(new BookSpecification(new SearchCriteria("status", "!=", Book.Status.DELETED.getValue())));
        return bookRepository.findAll(specification, pageable);
    }

    public Page<Book> findAllWaiting(Specification specification, Pageable pageable) {
        specification = specification
                .and(new BookSpecification(new SearchCriteria("status", "=", Book.Status.WAITING.getValue())));
        return bookRepository.findAll(specification, pageable);
    }


    @Override
    public List<Book> search(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public List<Book> bookComingSoon(int status, long createdAt) {
        return bookRepository.findAllByStatusAndCreatedAtMLS(2,0);
    }


    @Override
    public Book getById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book save(Book book) {
        book.setCreatedAtMLS(Calendar.getInstance().getTimeInMillis());
        book.setUpdatedAtMLS(Calendar.getInstance().getTimeInMillis());
        book.setStatus(Book.Status.ACTIVE.getValue());
        return bookRepository.save(book);
    }

    @Override
    public Book update(long id, Book updateBook) {
        Book existBook = bookRepository.findById(id).orElse(null);

        existBook.setName(updateBook.getName());
        existBook.setPhotos(updateBook.getPhotos());
        existBook.setDescription(updateBook.getDescription());
        existBook.setPrice(updateBook.getPrice());
        existBook.setQuantity(updateBook.getQuantity());
        existBook.setAuthor(updateBook.getAuthor());
        existBook.setPublisher(updateBook.getPublisher());
        existBook.setCategories(updateBook.getCategories());
        existBook.setUpdatedAtMLS(Calendar.getInstance().getTimeInMillis());
        return bookRepository.save(existBook);
    }

    @Override
    public boolean delete(long id) {
        Book existBook = bookRepository.findById(id).orElse(null);

        if (existBook == null) {
            return false;
        }
        existBook.setDeletedAtMLS(Calendar.getInstance().getTimeInMillis());
        existBook.setStatus(Book.Status.DELETED.getValue());
        bookRepository.save(existBook);
        return true;
    }



}
