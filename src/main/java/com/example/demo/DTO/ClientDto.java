package com.example.demo.DTO;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class ClientDto {
    private Long id;

    @NotNull(message = "Firstname is required!")
    private String firstname;
    @NotNull(message = "Lastname is required!")
    private String lastname;
    @NotNull(message = "email is required!")
    private String email;
    @NotNull(message = "Phone number is required!")
    private String phone;
    @NotNull(message = "Age is required!")
    private Long age;

    private String softwareName;
    private Set<SoftwareDto> softwareSet = new HashSet<>();
}
