package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.easymock.EasyMock.anyObject;
import static org.junit.jupiter.api.Assertions.*;

class AlquilaCochesTest {

    Ticket esperado, obtenido;
    TipoCoche tipoCoche;
    LocalDate inicio;
    int dias;
    float precioDia;
    MensajeException excepcion_obtenida;
    String mensaje_esperado;
    IService servicio;
    Calendario calendario;
    IMocksControl ctrl;
    AlquilaCoches alquilerMock;

    @BeforeEach
    void setUp(){
        ctrl = EasyMock.createStrictControl();
        calendario = ctrl.createMock(Calendario.class);
        servicio = ctrl.createMock(IService.class);
        alquilerMock = EasyMock.partialMockBuilder(AlquilaCoches.class).addMockedMethod("getServicio").createMock(ctrl);
    }


    @Test
    @Tag("tests1")
    void C1_calculaPrecio(){

        precioDia = 10f;
        tipoCoche = TipoCoche.TURISMO;
        dias = 10;
        esperado = new Ticket(); esperado.setPrecio_final(75);
        inicio = LocalDate.parse("2021-05-18");

        EasyMock.expect(alquilerMock.getServicio()).andReturn(servicio);
        EasyMock.expect(servicio.consultaPrecio(tipoCoche)).andReturn(precioDia);
        assertDoesNotThrow(()-> EasyMock.expect(calendario.es_festivo(anyObject())).andReturn(false).times(dias));

        alquilerMock.calendario = calendario;

        ctrl.replay();

        obtenido = assertDoesNotThrow(()-> alquilerMock.calculaPrecio(tipoCoche,inicio,dias));

        ctrl.verify();

        assertEquals(esperado,obtenido);
    }

    @Test
    @Tag("tests1")
    void C2_calculaPrecio() {

        precioDia = 10f;
        tipoCoche = TipoCoche.CARAVANA;
        dias = 7;
        esperado = new Ticket(); esperado.setPrecio_final(62.5f);
        inicio = LocalDate.parse("2021-06-19");

        EasyMock.expect(alquilerMock.getServicio()).andReturn(servicio);
        EasyMock.expect(servicio.consultaPrecio(tipoCoche)).andReturn(precioDia);
        assertDoesNotThrow(()-> EasyMock.expect(calendario.es_festivo(anyObject()))
                .andReturn(false)
                .times(1)
                .andReturn(true)
                .times(1)
                .andReturn(false)
                .times(3)
                .andReturn(true)
                .times(1)
                .andReturn(false)
                .times(1));

        alquilerMock.calendario = calendario;

        ctrl.replay();

        obtenido = assertDoesNotThrow(()-> alquilerMock.calculaPrecio(tipoCoche,inicio,dias));

        ctrl.verify();

        assertEquals(esperado,obtenido);
    }

    @Test
    @Tag("tests1")
    void C3_calculaPrecio(){

        precioDia = 10f;
        tipoCoche = TipoCoche.TURISMO;
        dias = 8;
        esperado = new Ticket(); esperado.setPrecio_final(62.5f);
        inicio = LocalDate.parse("2021-04-17");
        mensaje_esperado = "Error en dia: 2021-04-18; Error en dia: 2021-04-21; Error en dia: 2021-04-22; ";


        EasyMock.expect(alquilerMock.getServicio()).andReturn(servicio);
        EasyMock.expect(servicio.consultaPrecio(tipoCoche)).andReturn(precioDia);
        assertDoesNotThrow(()-> EasyMock.expect(calendario.es_festivo(anyObject()))
                .andReturn(false)
                .times(1)
                .andThrow(new CalendarioException())
                .times(1)
                .andReturn(false)
                .times(2)
                .andThrow(new CalendarioException())
                .times(2)
                .andReturn(false)
                .times(2));

        alquilerMock.calendario = calendario;

        ctrl.replay();

        excepcion_obtenida = assertThrows(MensajeException.class,()-> alquilerMock.calculaPrecio(tipoCoche,inicio,dias));

        ctrl.verify();

        assertEquals(mensaje_esperado,excepcion_obtenida.getMessage());
    }

    @Test
    void C3A_calculaPrecio(){

        precioDia = 10f;
        tipoCoche = TipoCoche.TURISMO;
        dias = 8;
        esperado = new Ticket(); esperado.setPrecio_final(62.5f);
        inicio = LocalDate.parse("2021-04-17");
        mensaje_esperado = "Error en dia: 2021-04-18; Error en dia: 2021-04-21; Error en dia: 2021-04-22; ";

        EasyMock.expect(alquilerMock.getServicio()).andReturn(servicio);
        EasyMock.expect(servicio.consultaPrecio(tipoCoche)).andReturn(precioDia);

        assertDoesNotThrow(()->EasyMock.expect(calendario.es_festivo(LocalDate.parse("2021-04-17"))).andReturn(false));
        assertDoesNotThrow(()->EasyMock.expect(calendario.es_festivo(LocalDate.parse("2021-04-18"))).andThrow(new CalendarioException()));
        assertDoesNotThrow(()->EasyMock.expect(calendario.es_festivo(LocalDate.parse("2021-04-19"))).andReturn(false));
        assertDoesNotThrow(()->EasyMock.expect(calendario.es_festivo(LocalDate.parse("2021-04-20"))).andReturn(false));
        assertDoesNotThrow(()->EasyMock.expect(calendario.es_festivo(LocalDate.parse("2021-04-21"))).andThrow(new CalendarioException()));
        assertDoesNotThrow(()->EasyMock.expect(calendario.es_festivo(LocalDate.parse("2021-04-22"))).andThrow(new CalendarioException()));
        assertDoesNotThrow(()->EasyMock.expect(calendario.es_festivo(LocalDate.parse("2021-04-23"))).andReturn(false));
        assertDoesNotThrow(()->EasyMock.expect(calendario.es_festivo(LocalDate.parse("2021-04-24"))).andReturn(false));

        alquilerMock.calendario = calendario;

        ctrl.replay();

        excepcion_obtenida = assertThrows(MensajeException.class,()-> alquilerMock.calculaPrecio(tipoCoche,inicio,dias));

        ctrl.verify();

        assertEquals(mensaje_esperado,excepcion_obtenida.getMessage());
    }

}