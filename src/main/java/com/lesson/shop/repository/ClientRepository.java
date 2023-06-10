package com.lesson.shop.repository;

import com.lesson.shop.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    List<ClientEntity> findByName(String name);

    List<ClientEntity> findByPhoneNumber(String phoneNumber);

    List<ClientEntity> findByNameAndPhoneNumber(String name, String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);
}
