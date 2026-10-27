package com.bertoti.airport.controller;

import com.bertoti.airport.model.Companhia;
import com.bertoti.airport.model.Passageiro;
import com.bertoti.airport.repository.CompanhiaRepository;
import com.bertoti.airport.repository.PassageiroRepository;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:5500"})
@RestController
@RequestMapping("/passageiros")
public class PassageiroController {

    private final PassageiroRepository repository;
    private final CompanhiaRepository companhias;

    public PassageiroController(PassageiroRepository repository, CompanhiaRepository companhias) {
        this.repository = repository;
        this.companhias = companhias;
    }

    @GetMapping
    Iterable<Passageiro> getPassageiros() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<Passageiro> getPassageiroById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<Passageiro> postPassageiro(@Valid @RequestBody Passageiro passageiro) {
        Companhia companhia = buscarCompanhia(passageiro.getCompanhia());
        if (companhia == null) {
            return ResponseEntity.badRequest().build();
        }
        passageiro.setCompanhia(companhia);
        return ResponseEntity.ok(repository.save(passageiro));
    }

    @PutMapping("/{id}")
    ResponseEntity<Passageiro> putPassageiro(@PathVariable String id, @Valid @RequestBody Passageiro passageiro) {
        Companhia companhia = buscarCompanhia(passageiro.getCompanhia());
        if (companhia == null) {
            return ResponseEntity.badRequest().build();
        }
        return repository.findById(id).map(p -> {
            p.setNome(passageiro.getNome());
            p.setCpf(passageiro.getCpf());
            p.setPassagem(passageiro.getPassagem());
            p.setPeso(passageiro.getPeso());
            p.setCompanhia(companhia);
            return ResponseEntity.ok(repository.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePassageiro(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Único campo com restrição de unicidade é o CPF
    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<String> cpfDuplicado() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já cadastrado");
    }

    // Recebe a companhia no corpo como {"id": "..."} e devolve a entidade real do banco
    private Companhia buscarCompanhia(Companhia informada) {
        if (informada == null) {
            return null;
        }
        return companhias.findById(informada.getId()).orElse(null);
    }
}
