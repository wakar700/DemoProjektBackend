package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.entity.Software;
import com.example.demo.entity.repository.ClientRepository;
import com.example.demo.entity.repository.SoftwareRepository;
import com.example.demo.service.GraphQLService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    SoftwareRepository softwareRepository;
    @Autowired
    GraphQLService graphQLService;

    //    ClientMapper clientMapper;
//    @PostMapping
//    public void getAllClients(@RequestBody String query) {
//        graphQLService.getGraphQL().execute(query);
//    }

    public DataFetcher createClient() {
        return dataFetchingEnvironment -> {
            Client client = new Client();
                client.setFirstname(dataFetchingEnvironment.getArgument("firstname"));
                client.setLastname(dataFetchingEnvironment.getArgument("lastname"));
                client.setEmail(dataFetchingEnvironment.getArgument("email"));
                client.setPhone(dataFetchingEnvironment.getArgument("phone"));
                Long age = Long.parseLong(dataFetchingEnvironment.getArgument("age"));
                client.setAge(age);
                String softwareName = dataFetchingEnvironment.getArgument("software");
            if (clientRepository.findByEmail(client.getEmail()).size() == 0 && clientRepository.findByPhone(client.getPhone()).size() == 0) {
                Client saveClient = clientRepository.save(client);
                Software softwareFromClient = softwareRepository.findByName(softwareName);

                    saveClient.addSoftware(softwareFromClient);
                    return clientRepository.save(saveClient);

//                client.addSoftware(newSoftware);
            }

            return null;
        };
    }

    public DataFetcher getClientById() {
        return dataFetchingEnvironment -> {
            String clientID = dataFetchingEnvironment.getArgument("id");
            Optional<Client> clientOptional = clientRepository.findById(Long.parseLong(clientID));
            if (clientOptional.isPresent()) {
                return clientOptional.get();
            }
            return null;
        };
    }


    public DataFetcher deleteSoftwareFromClient() {
        return dataFetchigEnvironment -> {
            String clientID = dataFetchigEnvironment.getArgument("idClient");
            String softwareID = dataFetchigEnvironment.getArgument("idSoftware");
            Optional<Software> softwareOptional = softwareRepository.findById(Long.parseLong(softwareID));
            if (softwareOptional.isPresent()) {
                Software software = softwareOptional.get();
                Optional<Client> clientOptional = software.getClientSet().stream().filter(client -> client.getId().equals(Long.parseLong(clientID))).findFirst();
                if (clientOptional.isPresent()) {
                    software.getClientSet().remove(clientOptional.get());
                    softwareRepository.save(software);
                        return 1;
                }
                return null;
            }
            return null;
        };
    }


    public DataFetcher deleteClient() {
        return dataFetchigEnvironment -> {
            String clientID = dataFetchigEnvironment.getArgument("id");
            Optional<Client> clientOptional = clientRepository.findById(Long.parseLong(clientID));
            if (clientOptional.isPresent()) {
                clientRepository.delete(clientOptional.get());
                return 1;
            }
            return null;
        };
    }


}
