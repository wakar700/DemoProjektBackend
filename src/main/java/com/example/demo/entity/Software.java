package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Software {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String price;
    private String name;
    private String system;
    @Column(length = 65535, columnDefinition = "text")
    private String disc;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Client_Software",
            joinColumns = {@JoinColumn(name = "software_id")},
            inverseJoinColumns = {@JoinColumn(name = "client_id")}
    )
    private Set<Client> clientSet = new HashSet<>();

    //    @ManyToOne
//    private Client client;
    @Autowired
    public Software(String name, String price, String system, String disc) {
        this.name = name;
        this.price = price;
        this.system = system;
        this.disc = disc;
    }

//    public void addClient(Client client) {
//        this.client=client;
//    }


}
