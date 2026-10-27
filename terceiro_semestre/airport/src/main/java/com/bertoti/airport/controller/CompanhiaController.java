package com.bertoti.airport.controller;

import com.bertoti.airport.model.Companhia;
import com.bertoti.airport.repository.CompanhiaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<Companhia> getCompanhiaById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    Companhia postCompanhia(@Valid @RequestBody Companhia companhia) {
        return repository.save(companhia);
    }

    @PutMapping("/{id}")
    ResponseEntity<Companhia> putCompanhia(@PathVariable String id, @Valid @RequestBody Companhia companhia) {
        return repository.findById(id).map(c -> {
            c.setNome(companhia.getNome());
            c.setQuantidadePassageiros(companhia.getQuantidadePassageiros());
            return ResponseEntity.ok(repository.save(c));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCompanhia(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
