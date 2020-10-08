package com.example.demo.Mapper;

import com.example.demo.DTO.ClientDto;
import com.example.demo.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {
    Client fromDto(ClientDto clientDto);

    ClientDto toDto(Client client);

    List<ClientDto> toDto(List<Client> clients);
}
