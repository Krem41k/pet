package com.example.pet.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocksDto {
    private Long id;
    @NotNull(message = "Поле не должно быть пустым")
    private String color;
    @NotNull(message = "Поле не должно быть пустым")
    @Min(0)
    @Max(100)
    private Integer cottonPart;
    @NotNull(message = "Поле не должно быть пустым")
    @Min(1)
    private Integer quantity;
}