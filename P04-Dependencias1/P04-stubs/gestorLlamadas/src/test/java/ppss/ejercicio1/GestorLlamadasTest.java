package ppss.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest {

    int minutos,hora,esperado;

    @Test
    void C1_calculaConsumo() {
        minutos = 10;
        hora = 15;
        esperado = 208;
        SubGestorLlamadas stub = new SubGestorLlamadas(hora);

        assertEquals(esperado,stub.calculaConsumo(minutos));
    }

    @Test
    void C2_calculaConsumo() {
        minutos = 10;
        hora = 22;
        esperado = 105;
        SubGestorLlamadas stub = new SubGestorLlamadas(hora);

        assertEquals(esperado,stub.calculaConsumo(minutos));
    }
}