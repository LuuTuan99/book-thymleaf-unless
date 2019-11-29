package com.fpt.service.admin;

import com.fpt.entity.Author;
import com.fpt.entity.Publisher;
import com.fpt.repository.PublisherRepository;
import com.fpt.specification.AuthorSpecification;
import com.fpt.specification.PublisherSpecification;
import com.fpt.specification.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Publisher> findAll(Pageable pageable) {
        return publisherRepository.findAll(pageable);
    }

    public Page<Publisher> findAllActive(Specification specification, Pageable pageable) {
        specification = specification
                .and(new PublisherSpecification(new SearchCriteria("status", "!=", Publisher.Status.DELETED.getValue())));
        return publisherRepository.findAll(specification, pageable);
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
        return publisherRepository.save(existPublisher);
    }

    @Override
    public boolean delete(long id) {
        Publisher existPublisher = publisherRepository.findById(id).orElse(null);
        if (existPublisher == null) {
            return false;
        }
        existPublisher.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        existPublisher.setStatus(Publisher.Status.DELETED.getValue());
        publisherRepository.save(existPublisher);
        return true;
    }
}
