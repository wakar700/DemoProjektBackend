package com.example.demo.Mapper;

import com.example.demo.DTO.SoftwareDto;
import com.example.demo.entity.Software;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SoftwareMapper {

    Software fromDto(SoftwareDto softwareDto);

    SoftwareDto toDto(Software software);

    List<SoftwareDto> toDto(List<Software> softwares);
}
