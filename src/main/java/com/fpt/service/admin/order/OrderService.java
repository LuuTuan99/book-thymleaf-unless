package com.fpt.service.admin.order;


import com.fpt.entity.OrderBook;

import java.util.List;

public interface OrderService {
    public List<OrderBook> findAll();
    public OrderBook create(OrderBook orderBook);
    public OrderBook getById(long id);
    public OrderBook update(long id,OrderBook updateOrderBook);
    public List<OrderBook> findByStatus(int status);
}
