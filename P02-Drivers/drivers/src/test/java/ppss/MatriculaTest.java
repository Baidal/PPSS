package ppss;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatriculaTest {

    int edad;
    boolean familiaNumerosa;
    boolean repetidor;
    float resultadoReal, resultadoEsperado;
    Matricula mat= new Matricula();

    @Tag("NOparam")
    @Test
    public void C1_calculaTasaMatricula() {
        edad = 19;
        familiaNumerosa = false;
        repetidor = true;
        resultadoEsperado = 2000;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);

        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Tag("NOparam")
    @Test
    void C2_calculaTasaMatricula() {
        edad = 68;
        familiaNumerosa = false;
        repetidor = true;
        resultadoEsperado = 250;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);

        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Tag("NOparam")
    @Test
    void C3_calculaTasaMatricula() {
        edad = 19;
        familiaNumerosa = true;
        repetidor = true;
        resultadoEsperado = 250;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);

        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Tag("NOparam")
    @Test
    void C4_calculaTasaMatricula() {
        edad = 19;
        familiaNumerosa = false;
        repetidor = false;
        resultadoEsperado = 500;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);

        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Tag("NOparam")
    @Test
    void C5_calculaTasaMatricula() {
        edad = 61;
        familiaNumerosa = false;
        repetidor = false;
        resultadoEsperado = 400;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);

        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

}