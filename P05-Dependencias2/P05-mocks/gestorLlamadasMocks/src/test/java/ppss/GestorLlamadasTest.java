package ppss;

import static org.junit.jupiter.api.Assertions.*;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GestorLlamadasTest {

    double esperado, obtenido;
    int minutos, hora;
    IMocksControl ctrl;
    Calendario cal;
    GestorLlamadas gestorMock;

    @BeforeEach
    void setup(){
        ctrl = EasyMock.createStrictControl();
        cal = ctrl.createMock(Calendario.class);
        gestorMock = EasyMock.partialMockBuilder(GestorLlamadas.class).addMockedMethod("getCalendario").createMock(ctrl);
    }


    @Test
    public void C1_calculaConsumo(){
        esperado = 457.6;
        minutos = 22; hora = 10;

        EasyMock.expect(gestorMock.getCalendario()).andReturn(cal);
        EasyMock.expect(cal.getHoraActual()).andReturn(hora);

        ctrl.replay();

        obtenido = gestorMock.calculaConsumo(minutos);

        assertEquals(esperado,obtenido,0.01);

        ctrl.verify();

    }

    @Test
    public void C2_calculaConsumo(){
        esperado = 136.5;
        minutos = 13; hora = 21;

        EasyMock.expect(gestorMock.getCalendario()).andReturn(cal);
        EasyMock.expect(cal.getHoraActual()).andReturn(hora);

        ctrl.replay();

        obtenido = gestorMock.calculaConsumo(minutos);

        assertEquals(esperado,obtenido,0.01);

        ctrl.verify();
    }

}