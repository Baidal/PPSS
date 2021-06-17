import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AlquilaCochesTest {

    String fechaInicio;
    LocalDate inicio;
    TipoCoche tipo;
    int dias;
    Ticket esperado, obtenido;

    @Test
    void C1_calculaPrecio(){
        obtenido = null;
        inicio = LocalDate.parse("2021-05-18");
        tipo = TipoCoche.TURISMO;
        dias = 10;
        esperado = new Ticket();
        esperado.setPrecio_final(75);

        //GENERAMOS STUBS
        ServicioStub servicio = new ServicioStub();
        servicio.setDevolver(10);

        CalendarioStub calendario = new CalendarioStub(inicio,dias);

        //INYECTAMOS STUBS
        AlquilaCocheTestable alquilaCoches = new AlquilaCocheTestable();

        alquilaCoches.setCalendario(calendario);

        alquilaCoches.setServicio(servicio);

        //EJECUTAMOS LOS TESTS
        obtenido = assertDoesNotThrow(() -> alquilaCoches.calculaPrecio(tipo, inicio, dias), "Se ha lanzado una excepcion");

        assertEquals(esperado,obtenido);

    }

    @Test
    void C2_calculaPrecio(){
        obtenido = null;
        inicio = LocalDate.parse("2021-06-19");
        tipo = TipoCoche.CARAVANA;
        dias = 7;
        esperado = new Ticket();
        esperado.setPrecio_final(62.5f);

        //GENERAMOS STUBS
        ServicioStub servicio = new ServicioStub();
        servicio.setDevolver(10);

        CalendarioStub calendario = new CalendarioStub(inicio,dias);
        calendario.addDiaFestivo("2021-06-20");
        calendario.addDiaFestivo("2021-06-24");

        //INYECTAMOS STUBS
        AlquilaCocheTestable alquilaCoches = new AlquilaCocheTestable();

        alquilaCoches.setCalendario(calendario);

        alquilaCoches.setServicio(servicio);

        //EJECUTAMOS LOS TESTS
        obtenido = assertDoesNotThrow(() -> alquilaCoches.calculaPrecio(tipo, inicio, dias), "Se ha lanzado una excepcion");

        assertEquals(esperado,obtenido);

    }

    @Test
    void C3_calculaPrecio(){
        obtenido = null;
        inicio = LocalDate.parse("2021-04-17");
        tipo = TipoCoche.TURISMO;
        dias = 8;
        String mensajeEsperado = "Error en dia: 2021-04-18; Error en dia: 2021-04-21; Error en dia: 2021-04-22; ";

        //GENERAMOS STUBS
        ServicioStub servicio = new ServicioStub();
        servicio.setDevolver(10);

        CalendarioStub calendario = new CalendarioStub(inicio,dias);
        calendario.addDiaExcepcion("2021-04-18");
        calendario.addDiaExcepcion("2021-04-21");
        calendario.addDiaExcepcion("2021-04-22");

        //INYECTAMOS STUBS
        AlquilaCocheTestable alquilaCoches = new AlquilaCocheTestable();

        alquilaCoches.setCalendario(calendario);

        alquilaCoches.setServicio(servicio);

        //EJECUTAMOS LOS TESTS
        MensajeException exception = assertThrows(MensajeException.class, () -> alquilaCoches.calculaPrecio(tipo,inicio,dias));



        assertEquals(mensajeEsperado,exception.getMessage());

    }

}