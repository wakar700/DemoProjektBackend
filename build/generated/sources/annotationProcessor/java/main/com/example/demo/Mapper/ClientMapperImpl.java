package com.example.demo.Mapper;

import com.example.demo.DTO.ClientDto;
import com.example.demo.entity.Client;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-02-24T12:44:31+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.5 (JetBrains s.r.o)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client fromDto(ClientDto clientDto) {
        if ( clientDto == null ) {
            return null;
        }

        Client client = new Client();

        return client;
    }

    @Override
    public ClientDto toDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDto clientDto = new ClientDto();

        return clientDto;
    }

    @Override
    public List<ClientDto> toDto(List<Client> clients) {
        if ( clients == null ) {
            return null;
        }

        List<ClientDto> list = new ArrayList<ClientDto>( clients.size() );
        for ( Client client : clients ) {
            list.add( toDto( client ) );
        }

        return list;
    }
}
