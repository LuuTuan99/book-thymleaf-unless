package com.fpt.service.admin.order;


import com.fpt.entity.OrderBook;
import com.fpt.repository.OrderRepository;
import com.fpt.specification.OrderSpecification;
import com.fpt.specification.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class OrderServiceImplement implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    public Page<OrderBook> findAllActive(Specification specification, Pageable pageable) {
        specification = specification
                .and(new OrderSpecification(new SearchCriteria("status", "!=", OrderBook.Status.DELETED.getValue())));
        return orderRepository.findAll(specification, pageable);
    }


    @Override
    public OrderBook create(OrderBook orderBook) {
        orderBook.setId(Calendar.getInstance().getTimeInMillis());
        orderBook.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        orderBook.setStatus(1);
        return orderRepository.save(orderBook);
    }

    @Override
    public OrderBook getById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public OrderBook update(long id, OrderBook updateOrderBook) {
        OrderBook existOrder = orderRepository.findById(id).orElse(null);
        existOrder.setStatus(updateOrderBook.getStatus());
        return orderRepository.save(existOrder);
    }

    @Override
    public List<OrderBook> findByStatus(int status) {
        return orderRepository.findAllByStatus(status);
    }

    @Override
    public List<OrderBook> findAll() {
        return orderRepository.findAll();
    }
}
