package com.fpt.service.admin;

import com.fpt.entity.Author;
import com.fpt.entity.Book;
import com.fpt.repository.BookRepository;
import com.fpt.service.admin.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public List<Book> search(String name) {
        return bookRepository.findByName(name);
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
//        existBook.setPublisher(updateBook.getPublisher());
//        existBook.setCategories(updateBook.getCategories());
        return bookRepository.save(updateBook);
    }

    @Override
    public boolean delete(long id) {
        Book existBook = bookRepository.findById(id).orElse(null);

        if (existBook == null) {
            return false;
        }
        bookRepository.delete(existBook);
        return true;
    }
}
