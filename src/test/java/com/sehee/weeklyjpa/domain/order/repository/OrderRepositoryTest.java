package com.sehee.weeklyjpa.domain.order.repository;

import com.sehee.weeklyjpa.domain.order.Member;
import com.sehee.weeklyjpa.domain.order.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.sehee.weeklyjpa.domain.order.OrderStatus.OPENED;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;
    Order order;
    Member member;

    @BeforeEach
    void setUp() {
        makeNewOrderWithoutMemberAndOrderItem();
        makeNewMemberWithoutOrder();

        order.setMember(member); // cascade = CascadeType.ALL

        orderRepository.save(order);
        log.info("Save order!");
    }

    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
    }

    void makeNewOrderWithoutMemberAndOrderItem() {
        order = new Order();
        order.setUuid(UUID.randomUUID().toString());
        order.setMemo("This is memo.");
        order.setOrderDatetime(LocalDateTime.now());
        order.setOrderStatus(OPENED);
        order.setCreatedAt(LocalDateTime.now());
        order.setCreatedBy("sh");
    }

    void makeNewMemberWithoutOrder() {
        member = new Member();
        member.setName("sehee");
        member.setNickName("LeeSehee");
        member.setAge(25);
        member.setAddress("Seoul");
        member.setDescription("This is description.");
        member.setCreatedAt(LocalDateTime.now());
        member.setCreatedBy("sh");
    }

    @Test
    @DisplayName("자동 생성되는 Spring data JPA 쿼리로 데이터를 쉽게 조작할 수 있다.")
    @Transactional
    void jpaQuery() {
        log.info("order is exists? {}", orderRepository.existsById(order.getUuid()));
        Order retrievedOrder = orderRepository.findById(order.getUuid()).get();
        List<Order> allOrders = orderRepository.findAll();
    }

    @Test
    @DisplayName("정의만하면 자동 구현되는 메소드 쿼리로 데이터를 쉽게 조작할 수 있다.")
    @Transactional
    void methodQuery() {
        orderRepository.findAllByOrderStatus(OPENED);
        orderRepository.findAllByOrderStatusOrderByOrderDatetime(OPENED);
    }

    @Test
    @DisplayName("커스텀 쿼리를 통해 더 세밀하게 데이터를 쉽게 조작할 수 있다.")
    @Transactional
    void namedQuery() {
        Order retrievedOrder = orderRepository.findByMemo(order.getMemo()).get();
        assertThat(retrievedOrder.getMemo()).isEqualTo(order.getMemo());
    }
}