package com.example.demo.entity;


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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private Long age;
    private String softwareName;

    //    @OneToMany(mappedBy = "client")
    @ManyToMany(mappedBy = "clientSet", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Software> softwareSet = new HashSet<>();

    @Autowired
    public Client(String firstname, String lastname, String email, String phone, Long age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }

    public void addSoftware(Software software) {
        softwareSet.add(software);
        software.getClientSet().add(this);
    }
}
