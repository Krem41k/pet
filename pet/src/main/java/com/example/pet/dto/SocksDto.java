package com.example.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class SocksDto {
    private Long id;
    private String color;
    private Integer colorPart;
}