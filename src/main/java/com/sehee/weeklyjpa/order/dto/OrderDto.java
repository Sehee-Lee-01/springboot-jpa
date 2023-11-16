package com.sehee.weeklyjpa.order.dto;

import com.sehee.weeklyjpa.domain.order.OrderStatus;
import com.sehee.weeklyjpa.member.dto.MemberDto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        String uuid,
        LocalDateTime orderDateTime,
        OrderStatus orderStatus,
        String memo,
        MemberDto memberDto,
        List<OrderItemDto> orderItemDtos
) {
}
