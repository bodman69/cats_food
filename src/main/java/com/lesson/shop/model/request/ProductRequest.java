package com.lesson.shop.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductRequest {
    @NotBlank(message = "Name can't be empty")
    private String name;

    @NotNull(message = "Price can't be empty")
    @Positive(message = "Price can't be zero or negative")
    private BigDecimal price;
    @NotNull(message = "Count can't be empty")
    @PositiveOrZero(message = "Count can't be negative")
    private Long count;
}