package com.bertoti.airport.controller;

import com.bertoti.airport.model.Aviao;
import com.bertoti.airport.model.Companhia;
import com.bertoti.airport.repository.AviaoRepository;
import com.bertoti.airport.repository.CompanhiaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:5500"})
@RestController
@RequestMapping("/avioes")
public class AviaoController {

    private final AviaoRepository repository;
    private final CompanhiaRepository companhias;

    public AviaoController(AviaoRepository repository, CompanhiaRepository companhias) {
        this.repository = repository;
        this.companhias = companhias;
    }

    @GetMapping
    Iterable<Aviao> getAvioes() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<Aviao> getAviaoById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<Aviao> postAviao(@Valid @RequestBody Aviao aviao) {
        Companhia companhia = buscarCompanhia(aviao.getCompanhia());
        if (companhia == null) {
            return ResponseEntity.badRequest().build();
        }
        aviao.setCompanhia(companhia);
        return ResponseEntity.ok(repository.save(aviao));
    }

    @PutMapping("/{id}")
    ResponseEntity<Aviao> putAviao(@PathVariable String id, @Valid @RequestBody Aviao aviao) {
        Companhia companhia = buscarCompanhia(aviao.getCompanhia());
        if (companhia == null) {
            return ResponseEntity.badRequest().build();
        }
        return repository.findById(id).map(a -> {
            a.setNome(aviao.getNome());
            a.setPrefixo(aviao.getPrefixo());
            a.setPeso(aviao.getPeso());
            a.setAnoFabricacao(aviao.getAnoFabricacao());
            a.setCapacidadePassageiros(aviao.getCapacidadePassageiros());
            a.setCompanhia(companhia);
            return ResponseEntity.ok(repository.save(a));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAviao(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Recebe a companhia no corpo como {"id": "..."} e devolve a entidade real do banco
    private Companhia buscarCompanhia(Companhia informada) {
        if (informada == null) {
            return null;
        }
        return companhias.findById(informada.getId()).orElse(null);
    }
}
