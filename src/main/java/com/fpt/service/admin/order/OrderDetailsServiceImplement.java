package com.fpt.service.admin.order;


import com.fpt.entity.OrderDetails;
import com.fpt.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImplement implements OrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Override
    public List<OrderDetails> fillAll() {
        return orderDetailsRepository.findAll();
    }

    @Override
    public OrderDetails create(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }
}
