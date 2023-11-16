package com.sehee.weeklyjpa.member.dto;

public record MemberDto(
        Long id,
        String name,
        String nickName,
        int age,
        String address,
        String description
) {
}
