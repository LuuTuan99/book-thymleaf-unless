package com.fpt.repository;


import com.fpt.entity.OrderDetails;
import com.fpt.entity.OrderDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsId> {
}
