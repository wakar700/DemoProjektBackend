package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.entity.Software;
import com.example.demo.entity.repository.ClientRepository;
import com.example.demo.entity.repository.SoftwareRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientAllDataFetcher implements DataFetcher<List<Client>> {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> get(DataFetchingEnvironment environment) {
        return clientRepository.findAll();
    }
}
