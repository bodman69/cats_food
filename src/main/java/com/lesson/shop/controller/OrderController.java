package com.lesson.shop.controller;

import com.lesson.shop.model.OrderModel;
import com.lesson.shop.model.request.OrderRequest;
import com.lesson.shop.model.request.OrderUpdateRequest;
import com.lesson.shop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@AllArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<Void> create(@RequestBody OrderRequest request) {
        orderService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/orders")
    public List<OrderModel> getAll(){
        return orderService.getAllOrders();
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/orders/{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable Long id, @RequestBody OrderUpdateRequest orderUpdateRequest){
        orderService.updateOrder(id, orderUpdateRequest);
        return ResponseEntity.noContent().build();
    }

}
