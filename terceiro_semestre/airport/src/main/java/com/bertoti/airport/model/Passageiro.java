package com.bertoti.airport.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
public class Passageiro {

    @Id
    private String id = UUID.randomUUID().toString();

    @NotBlank
    private String nome;

    @NotBlank
    @Column(unique = true)
    private String cpf;

    private String passagem;

    private double peso;

    @ManyToOne
    @JoinColumn(name = "companhia_id")
    @JsonIgnoreProperties({"avioes", "passageiros"})
    private Companhia companhia;

    public Passageiro() {
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getPassagem() { return passagem; }
    public void setPassagem(String passagem) { this.passagem = passagem; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public Companhia getCompanhia() { return companhia; }
    public void setCompanhia(Companhia companhia) { this.companhia = companhia; }
}