package com.lesson.shop.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class OrderRequest {

    @NotNull
    private Long clientId;

    private List<OrderProductRequest> products;

}
