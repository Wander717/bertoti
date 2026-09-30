package com.bertoti.airport.controller;

import com.bertoti.airport.model.Companhia;
import com.bertoti.airport.repository.CompanhiaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:5500"})
@RestController
@RequestMapping("/companhias")
public class CompanhiaController {

    private final CompanhiaRepository repository;

    public CompanhiaController(CompanhiaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    Iterable<Companhia> getCompanhias() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Companhia> getCompanhiaById(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping
    Companhia postCompanhia(@RequestBody Companhia companhia) {
        return repository.save(companhia);
    }

    @PutMapping("/{id}")
    ResponseEntity<Companhia> putCompanhia(@PathVariable String id, @RequestBody Companhia companhia) {
        boolean existia = repository.existsById(id);
        repository.findById(id).ifPresent(c -> {
            c.setNome(companhia.getNome());
            c.setQuantidadePassageiros(companhia.getQuantidadePassageiros());
            repository.save(c);
        });

        if (!existia) {
            Companhia salva = repository.save(companhia);
            return new ResponseEntity<>(salva, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(repository.findById(id).orElse(companhia), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteCompanhia(@PathVariable String id) {
        repository.deleteById(id);
    }
}