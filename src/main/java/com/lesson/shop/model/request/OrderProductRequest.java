package com.lesson.shop.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderProductRequest {

    @NotNull
    private Long productId;
    @NotNull
    private Long count;


}
