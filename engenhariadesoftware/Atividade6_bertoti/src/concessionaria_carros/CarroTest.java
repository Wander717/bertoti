package concessionaria_carros;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CarroTest {

    @Test
    void deveCriarCarroComDadosCorretos() {

        Carro carro = new Carro("Civic", "Honda", 2022);

        assertEquals("Civic", carro.getModelo());
        assertEquals("Honda", carro.getFabricante());
        assertEquals(2022, carro.getAno());
    }
}