package com.bertoti.airport.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
public class Aviao {

    @Id
    private String id = UUID.randomUUID().toString();

    @NotBlank
    private String nome;

    @NotBlank
    private String prefixo;

    private double peso;

    private int anoFabricacao;

    private int capacidadePassageiros;

    @ManyToOne
    @JoinColumn(name = "companhia_id")
    @JsonIgnoreProperties({"avioes", "passageiros"})
    private Companhia companhia;

    public Aviao() {
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getPrefixo() { return prefixo; }
    public void setPrefixo(String prefixo) { this.prefixo = prefixo; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public int getAnoFabricacao() { return anoFabricacao; }
    public void setAnoFabricacao(int anoFabricacao) { this.anoFabricacao = anoFabricacao; }
    public int getCapacidadePassageiros() { return capacidadePassageiros; }
    public void setCapacidadePassageiros(int capacidadePassageiros) { this.capacidadePassageiros = capacidadePassageiros; }
    public Companhia getCompanhia() { return companhia; }
    public void setCompanhia(Companhia companhia) { this.companhia = companhia; }
}