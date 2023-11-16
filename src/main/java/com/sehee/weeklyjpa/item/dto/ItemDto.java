package com.sehee.weeklyjpa.item.dto;

public record ItemDto(
         Long id,
         int price,
         int stockQuantity,

         ItemType type,

         String chef,
         Integer power,
         Integer width,
         Integer height
) {
}
