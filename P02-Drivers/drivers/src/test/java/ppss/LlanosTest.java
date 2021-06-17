package ppss;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LlanosTest {

    ArrayList<Integer> lecturas ;
    Llanos llano = new Llanos();

    @Tag("tablaA")
    @Tag("NOparam")
    @Test
    void C1A_buscarTramoLlanoMasLargo() {
        lecturas = new ArrayList<>();
        lecturas.add(1);

        Tramo tram_esperado = new Tramo();

        assertEquals(tram_esperado,llano.buscarTramoLlanoMasLargo(lecturas));
    }

    @Tag("tablaA")
    @Tag("NOparam")
    @Test
    void C2A_buscarTramoLlanoMasLargo() {
        lecturas = new ArrayList<>();
        lecturas.add(1);
        lecturas.add(1);

        Tramo tram_esperado = new Tramo(1,0);

        assertEquals(tram_esperado,llano.buscarTramoLlanoMasLargo(lecturas));
    }

    @Tag("tablaA")
    @Tag("NOparam")
    @Test
    void C3A_buscarTramoLlanoMasLargo() {
        lecturas = new ArrayList<>();
        lecturas.add(1);
        lecturas.add(1);
        lecturas.add(1);

        Tramo tram_esperado = new Tramo(2,0);

        assertEquals(tram_esperado,llano.buscarTramoLlanoMasLargo(lecturas));

    }

    @Tag("tablaB")
    @Tag("NOparam")
    @Test
    void C1B_buscarTramoLlanoMasLargo() {
        lecturas = new ArrayList<>();
        lecturas.add(-1);

        Tramo tram_esperado = new Tramo();

        assertEquals(tram_esperado,llano.buscarTramoLlanoMasLargo(lecturas));
    }

    @Tag("tablaB")
    @Tag("NOparam")
    @Test
    void C2B_buscarTramoLlanoMasLargo() {
        lecturas = new ArrayList<>();
        lecturas.add(-1);
        lecturas.add(-1);
        lecturas.add(-1);
        lecturas.add(-1);

        Tramo tram_esperado = new Tramo(3,0);

        assertEquals(tram_esperado,llano.buscarTramoLlanoMasLargo(lecturas));
    }

    @Tag("tablaB")
    @Tag("NOparam")
    @Test
    void C3B_buscarTramoLlanoMasLargo() {
        lecturas = new ArrayList<>();
        lecturas.add(120);
        lecturas.add(140);
        lecturas.add(-10);
        lecturas.add(-10);
        lecturas.add(-10);

        Tramo tram_esperado = new Tramo(2,2);

        assertEquals(tram_esperado,llano.buscarTramoLlanoMasLargo(lecturas));
    }



}