package ppss;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatriculaTest {
    int edad;
    boolean familiaNumerosa;
    boolean repetidor;
    float resultadoReal, resultadoEsperado;
    Matricula mat= new Matricula();

    //CASO EDAD < 25, REPETIDOR, FAMILIA NUMEROSA
    @Test
    public void testCalculaTasaMatricula() {
        edad = 23;
        familiaNumerosa = true;
        repetidor = true;
        resultadoEsperado = 250.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        //el tercer parámetro del método Assert.assertEquals es necesario si estamos comparando "floats"
        //en este caso el método devuelve cierto si:
        //resultadoEsperado = resultadoReal +/- 0.002
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    //SI EDAD >= 66, TASA/2 INDEPENDIENTEMENTE DEL RESTO DE PARAMS
    @Test
    public void testCalculaTasaMatricula_C2() {
        edad = 65;
        familiaNumerosa = false;
        repetidor = true;
        resultadoEsperado = 250.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);

        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    //CASO EN EL QUE EDAD < 25, REPETIDOR Y NO ES FAMILIA NUMEROSA
    @Test
    public void testCalculaTasaMatricula_C3() {
        edad = 23;
        familiaNumerosa = false;
        repetidor = true;
        resultadoEsperado = 2000.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);

        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    //CASO EN EL QUE EDAD < 25, REPETIDOR Y NO ES FAMILIA NUMEROSA
    @Test
    public void testCalculaTasaMatricula_C4() {
        edad = 55;
        familiaNumerosa = true;
        repetidor = false;
        resultadoEsperado = 400.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);

        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    //CASO EN EL QUE EDAD < 25, REPETIDOR Y NO ES FAMILIA NUMEROSA
    @Test
    public void testCalculaTasaMatricula_C6() {
        edad = 55;
        familiaNumerosa = false;
        repetidor = false;
        resultadoEsperado = 400.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);

        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

}
