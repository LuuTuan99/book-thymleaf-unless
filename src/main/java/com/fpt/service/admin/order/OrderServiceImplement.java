package com.fpt.service.admin.order;

import com.fpt.entity.Author;
import com.fpt.entity.Book;
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
