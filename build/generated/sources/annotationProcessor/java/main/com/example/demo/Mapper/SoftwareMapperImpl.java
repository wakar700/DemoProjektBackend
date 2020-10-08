package com.example.demo.Mapper;

import com.example.demo.DTO.SoftwareDto;
import com.example.demo.entity.Software;
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
public class SoftwareMapperImpl implements SoftwareMapper {

    @Override
    public Software fromDto(SoftwareDto softwareDto) {
        if ( softwareDto == null ) {
            return null;
        }

        Software software = new Software();

        return software;
    }

    @Override
    public SoftwareDto toDto(Software software) {
        if ( software == null ) {
            return null;
        }

        SoftwareDto softwareDto = new SoftwareDto();

        return softwareDto;
    }

    @Override
    public List<SoftwareDto> toDto(List<Software> softwares) {
        if ( softwares == null ) {
            return null;
        }

        List<SoftwareDto> list = new ArrayList<SoftwareDto>( softwares.size() );
        for ( Software software : softwares ) {
            list.add( toDto( software ) );
        }

        return list;
    }
}
