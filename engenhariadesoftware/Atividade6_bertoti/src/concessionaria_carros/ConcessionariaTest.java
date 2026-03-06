package concessionaria_carros;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ConcessionariaTest {

    @Test
    void deveAdicionarCarroNaConcessionaria() {

        Concessionaria concessionaria = new Concessionaria();

        Carro carro = new Carro("Corolla", "Toyota", 2023);

        concessionaria.adicionarCarro(carro);

        assertEquals(1, concessionaria.quantidadedeCarros());
    }

    @Test
    void deveContarVariosCarros() {

        Concessionaria concessionaria = new Concessionaria();

        concessionaria.adicionarCarro(new Carro("Civic", "Honda", 2022));
        concessionaria.adicionarCarro(new Carro("Gol", "Volkswagen", 2020));

        assertEquals(2, concessionaria.quantidadedeCarros());
    }
}