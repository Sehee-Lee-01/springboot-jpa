package com.sehee.weeklyjpa.domain.order.repository;

import com.sehee.weeklyjpa.domain.order.Order;
import com.sehee.weeklyjpa.domain.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByOrderStatus(OrderStatus orderStatus);

    List<Order> findAllByOrderStatusOrderByOrderDatetime(OrderStatus orderStatus);

    @Query("SELECT o FROM Order o WHERE o.memo LIKE %?1%") // JPQL
    Optional<Order> findByMemo(String memo);
}
