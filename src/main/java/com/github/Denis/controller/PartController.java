package com.github.Denis.controller;

import com.github.Denis.entity.Part;
import com.github.Denis.repository.PartRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController

@RequestMapping
public class PartController {

    @PersistenceContext
    private EntityManager entityManager;

    private final PartRepository partRepository;

    @Autowired
    PartController(PartRepository partRepository){
        this.partRepository = partRepository;
    }
    // Read
    @GetMapping("/parts")

    public List<Part> getPart() {
        return partRepository.findAll();
    }

    // Read by id
    @GetMapping("/parts/{id}")
    public Part getPartByID(@PathVariable int id) {
        return partRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Entity not found"));
    }

    //    Create / Update
    @PostMapping("/parts")
    @Transactional
    public int saveNewPart(@RequestBody @Valid Part part) {
        part = partRepository.save(part);
        return part.getId();
    }

    //    Delete
    @DeleteMapping("/parts/{id}")
    public void deletePart(@PathVariable int id) {
        partRepository.deleteById(id);
    }

}
