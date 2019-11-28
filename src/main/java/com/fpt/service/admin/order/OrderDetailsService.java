package com.fpt.service.admin.order;


import com.fpt.entity.OrderDetails;

import java.util.List;

public interface OrderDetailsService {
    public List<OrderDetails> fillAll();
    public OrderDetails create(OrderDetails orderDetails);
}
