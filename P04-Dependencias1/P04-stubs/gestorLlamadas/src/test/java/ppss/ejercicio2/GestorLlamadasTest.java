package ppss.ejercicio2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest {

    int hora,minutos,esperado;

    @Test
    void C1_calculaConsumo(){
        minutos=10;
        hora=15;
        esperado=208;

        StubGestorLlamadasTest gestorLlamadasTest = new StubGestorLlamadasTest(hora);

        assertEquals(esperado, gestorLlamadasTest.calculaConsumo(minutos));

    }

    @Test
    void C2_calculaConsumo(){
        minutos=10;
        hora=22;
        esperado=105;

        StubGestorLlamadasTest gestorLlamadasTest = new StubGestorLlamadasTest(hora);

        assertEquals(esperado, gestorLlamadasTest.calculaConsumo(minutos));

    }


}