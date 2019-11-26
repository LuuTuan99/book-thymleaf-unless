package com.fpt.repository;


import com.fpt.entity.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderBook, Long> {
}
