package com.fpt.service;

import com.fpt.entity.Publisher;
import com.fpt.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    PublisherRepository publisherRepository;

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public List<Publisher> search(String name) {
        return publisherRepository.findByName(name);
    }

    @Override
    public Publisher getById(long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    @Override
    public Publisher save(Publisher publisher) {
        publisher.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        publisher.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        publisher.setStatus(Publisher.Status.ACTIVE.getValue());
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher update(long id, Publisher updatePublisher) {
        Publisher existPublisher = publisherRepository.findById(id).orElse(null);

        existPublisher.setName(updatePublisher.getName());
        existPublisher.setAvatar(updatePublisher.getAvatar());
        existPublisher.setDescription(updatePublisher.getDescription());
        return publisherRepository.save(updatePublisher);
    }

    @Override
    public boolean delete(long id) {
        Publisher existPublisher = publisherRepository.findById(id).orElse(null);
        if (existPublisher == null) {
            return false;
        }
        publisherRepository.delete(existPublisher);
        return true;
    }
}
