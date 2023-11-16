package com.sehee.weeklyjpa.order.dto;

import com.sehee.weeklyjpa.item.dto.ItemDto;

public record OrderItemDto(
         Long id,
         Integer price,
         Integer quantity,
         ItemDto itemDto
) {
}
