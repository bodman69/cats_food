package org.example.service;

import org.example.entity.ClientEntity;
import org.example.repository.ClientRepository;

import java.util.List;

public class ClientService {

    public static final String A = "\u001B[36m";
    public static final String R = "\u001B[0m";
    private ClientRepository clientRepository;

    public ClientService() {
        clientRepository = new ClientRepository();
    }

    public void save(String name, String phoneNumber) {
        if (clientRepository.existByPhoneNumber(phoneNumber)) {
            System.err.println("Client with phone number " + A + phoneNumber + R + " already exist");
        } else {
            clientRepository.saveClient(name, phoneNumber);
            System.out.println("Client " + A + name + " : " + phoneNumber + R + " successfully saved");
        }
    }

    public List<ClientEntity> findAll() {
        return clientRepository.findAllClients();
    }

    public void update(long id, String newName, String newPhoneNumber) {
        ClientEntity clientEntity = clientRepository.findClientById(id);
        if (newName != null && !newName.equals("")) {
            clientEntity.setName(newName);
        }
        if (newPhoneNumber != null && !newPhoneNumber.equals("")) {
            clientEntity.setPhoneNumber(newPhoneNumber);
        }
        clientRepository.updateClient(clientEntity);
    }

    public void delete(long id) {
        if (!clientRepository.existClientById(id)) {
            System.err.println("Client not exist");
        } else {
            clientRepository.deleteClientById(id);
        }
    }

    public boolean existByClientId(long clientId) {
        return clientRepository.existClientById(clientId);
    }
}
