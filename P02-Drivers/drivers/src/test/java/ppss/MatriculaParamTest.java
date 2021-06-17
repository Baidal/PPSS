package ppss;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MatriculaParamTest {
    Matricula mat = new Matricula();

    private static Stream<Arguments> casosDePrueba(){
        return Stream.of(
                Arguments.of(19,false,true,2000),
                Arguments.of(68,false,true,250),
                Arguments.of(19,true,true,250),
                Arguments.of(19,false,false,500),
                Arguments.of(60,true,true,400)
        );

    }

    @Tag("param")
    @ParameterizedTest()
    @MethodSource("casosDePrueba")
    void calculaTasaMatricula(int edad, boolean familiaNumerosa, boolean repetidor, double esperado) {

        assertEquals(esperado,mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor),0.002f);
    }
}