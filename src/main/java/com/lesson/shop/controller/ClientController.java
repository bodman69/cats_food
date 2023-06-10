package com.lesson.shop.controller;

import com.lesson.shop.model.entity.ClientEntity;
import com.lesson.shop.model.request.ClientRequest;
import com.lesson.shop.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/client")
    public ResponseEntity<Void> create(@RequestBody @Valid ClientRequest request) {
        clientService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/client")
    public List<ClientEntity> getAll(@Valid @RequestParam(required = false) String name,
                                     @Valid @RequestParam(required = false) String phoneNumber) {
        return clientService.getAll(name, phoneNumber);
    }

    @PutMapping("/client/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid ClientRequest request) {
        clientService.update(id, request);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
