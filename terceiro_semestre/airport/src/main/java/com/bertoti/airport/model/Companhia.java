package com.bertoti.airport.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Companhia {

    @Id
    private String id = UUID.randomUUID().toString();

    @NotBlank
    private String nome;

    private int quantidadePassageiros;

    @OneToMany(mappedBy = "companhia", cascade = CascadeType.ALL)
    private List<Aviao> avioes = new ArrayList<>();

    @OneToMany(mappedBy = "companhia", cascade = CascadeType.ALL)
    private List<Passageiro> passageiros = new ArrayList<>();

    public Companhia() {
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getQuantidadePassageiros() { return quantidadePassageiros; }
    public void setQuantidadePassageiros(int quantidadePassageiros) { this.quantidadePassageiros = quantidadePassageiros; }
    public List<Aviao> getAvioes() { return avioes; }
    public List<Passageiro> getPassageiros() { return passageiros; }
}