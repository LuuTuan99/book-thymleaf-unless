package com.fpt.service.admin.order;

import com.fpt.entity.Author;
import com.fpt.entity.OrderBook;
import com.fpt.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class OrderServiceImplement implements OrderService{
    @Autowired
    private OrderRepository orderRepository;



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
    public List<OrderBook> findAll() {
        return orderRepository.findAll();
    }
}
