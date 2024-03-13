package com.study.mvc.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StudentRespDto {
    private String name;
    private int age;
    private String phone;
    private String address;
}
