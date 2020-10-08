package com.example.demo.entity.repository;

import com.example.demo.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findByEmail(String email);
    List<Client> findByPhone(String phone);
    public List<Client> findAll();
    Optional<Client> findById(Long id);
}
