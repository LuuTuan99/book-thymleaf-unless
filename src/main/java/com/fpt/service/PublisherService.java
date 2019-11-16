package com.fpt.service;

import com.fpt.entity.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PublisherService {
    List<Publisher> findAll();

    List<Publisher> search(String name);

    Publisher getById(long id);

    Publisher save(Publisher publisher);

    Publisher update(long id, Publisher updatePublisher);

    boolean delete(long id);
}
