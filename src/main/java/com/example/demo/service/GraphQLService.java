package com.example.demo.service;

import com.example.demo.controller.ClientController;
import com.example.demo.controller.SoftwareController;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLService {

    @Autowired
    ClientAllDataFetcher clientAllDataFetcher;
    @Autowired
    SoftwareAllDataFetcher softwareAllDataFetcher;
    @Autowired
    ClientController clientController;
    @Autowired
    SoftwareController softwareController;

    private GraphQL graphQL;
    @Bean
    public GraphQL getGraphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("allClients", clientAllDataFetcher))
                .type(newTypeWiring("Query")
                        .dataFetcher("allSoftwares", softwareAllDataFetcher))
                .type(newTypeWiring("Query")
                        .dataFetcher("clientById", clientController.getClientById()))
                .type(newTypeWiring("Query")
                        .dataFetcher("softwareById", softwareController.getSoftwareById()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("deleteClient", clientController.deleteClient()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("createClient", clientController.createClient()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("deleteSoftwareFromClient", clientController.deleteSoftwareFromClient()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("deleteSoftware", softwareController.deleteSoftware()))
                .build();
    }

}
