package com.example.demo.controller;


import com.example.demo.DTO.SoftwareDto;
import com.example.demo.Mapper.SoftwareMapper;
import com.example.demo.entity.Client;
import com.example.demo.entity.Software;
import com.example.demo.entity.repository.SoftwareRepository;
import com.example.demo.service.GraphQLService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class SoftwareController {
    @Autowired
    GraphQLService graphQLService;
    @Autowired
    SoftwareRepository softwareRepository;

    @RequestMapping
    public void getAllSoftwares(@RequestBody String query) {
        graphQLService.getGraphQL().execute(query);
    }

    public DataFetcher getSoftwareById() {
        return dataFetchingEnvironment -> {
            String softwareID = dataFetchingEnvironment.getArgument("id");
            Optional<Software> softwareOptional = softwareRepository.findById(Long.parseLong(softwareID));
            if (softwareOptional.isPresent()) {
                return softwareOptional.get();
            }
            return null;
        };
    }

    public DataFetcher deleteSoftware() {
        return dataFetchigEnvironment -> {
            String clientID = dataFetchigEnvironment.getArgument("id");
            Optional<Software> softwareOptional = softwareRepository.findById(Long.parseLong(clientID));
            if (softwareOptional.isPresent()) {
                softwareRepository.delete(softwareOptional.get());
            }
            return null;
        };
    }

}
