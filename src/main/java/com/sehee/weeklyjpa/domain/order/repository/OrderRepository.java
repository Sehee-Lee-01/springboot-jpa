package com.sehee.weeklyjpa.domain.order.repository;

import com.sehee.weeklyjpa.domain.order.Order;
import com.sehee.weeklyjpa.domain.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

}
