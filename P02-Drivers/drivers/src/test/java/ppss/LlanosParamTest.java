package ppss;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LlanosParamTest {

    ArrayList<Integer> lecturas ;
    Llanos llano = new Llanos();



    private static Stream<Arguments> casosDePrueba(){
        return Stream.of(
                Arguments.of(new ArrayList<Integer>(Arrays.asList(1)), new Tramo(0,0)),
                Arguments.of(new ArrayList<Integer>(Arrays.asList(1,1)), new Tramo(1,0)),
                Arguments.of(new ArrayList<Integer>(Arrays.asList(1,1,1)), new Tramo(2,0)),
                Arguments.of(new ArrayList<Integer>(Arrays.asList(-1)), new Tramo(0,0)),
                Arguments.of(new ArrayList<Integer>(Arrays.asList(-1,-1,-1,-1)), new Tramo(3,0)),
                Arguments.of(new ArrayList<Integer>(Arrays.asList(120,140,-10,-10,-10)), new Tramo(2,2))
        );
    }

    @Tag("param")
    @ParameterizedTest
    @MethodSource("casosDePrueba")
    void buscarTramoLlanoMasLargo(ArrayList<Integer> lecturas, Tramo resultado_esperado) {
        assertEquals(resultado_esperado,llano.buscarTramoLlanoMasLargo(lecturas));
    }
}