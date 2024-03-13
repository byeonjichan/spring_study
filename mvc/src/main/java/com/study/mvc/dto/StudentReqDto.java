package com.study.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class StudentReqDto {
    private String name;
    private int age;
    private String phone;
    private String address;

    public StudentRespDto toRespDto() {
        return StudentRespDto.builder()
                .name(name)
                .age(age)
                .phone(phone)
                .address(address)
                .build();
    }
}
