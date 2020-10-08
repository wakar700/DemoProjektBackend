package com.example.demo.entity.repository;

import com.example.demo.entity.Software;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SoftwareRepository extends CrudRepository<Software, Long> {

    public List<Software> findAll();
    Software findByName(String softName);
}
