package com.fpt.service.admin.order;

import com.fpt.entity.OrderBook;
import com.fpt.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

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
}
