package com.fpt.repository;


import com.fpt.entity.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderBook, Long> {
    List<OrderBook> findAllByStatus(int status);
}
