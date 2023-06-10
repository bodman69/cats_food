package com.lesson.shop.service;

import com.lesson.shop.exception.BadRequestException;
import com.lesson.shop.exception.EntityNotFoundException;
import com.lesson.shop.model.entity.ClientEntity;
import com.lesson.shop.model.request.ClientRequest;
import com.lesson.shop.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientEntity create(ClientRequest clientRequest) {
        if (clientRepository.existsByPhoneNumber(clientRequest.getPhoneNumber())) {
            throw new BadRequestException("client with number " + clientRequest.getPhoneNumber() + " already exist");
        }
        ClientEntity client = ClientEntity.builder()
                .name(clientRequest.getName())
                .phoneNumber(clientRequest.getPhoneNumber())
                .build();

        return clientRepository.save(client);
    }

    public List<ClientEntity> getAll(String name, String phoneNumber) {
        if (name == null && phoneNumber == null) {
            return clientRepository.findAll();
        }
        if (name != null && phoneNumber == null) {
            return clientRepository.findByName(name);
        }
        if (name == null && phoneNumber != null) {
            return clientRepository.findByPhoneNumber(phoneNumber);
        }
        return clientRepository.findByNameAndPhoneNumber(name, phoneNumber);
    }

    public void update(Long id, ClientRequest request) {
        ClientEntity product = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client with id " + id + " doesn't exist"));

        product.setName(request.getName());
        product.setPhoneNumber(request.getPhoneNumber());
        clientRepository.save(product);
    }

    public void deleteById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Client with id " + id + " doesn't exist");
        }
        clientRepository.deleteById(id);
    }

    public boolean existById(Long id) {
        return clientRepository.existsById(id);
    }

}
