package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PremioTest {

    String esperado, esperado_cliente, obtenido;
    float esperado_generador;


    @Test
    public void C1_compruebaPremio() throws ClienteWebServiceException {
        esperado = "Premiado con entrada final Champions";
        esperado_generador = 0.07f;
        esperado_cliente = "entrada final Champions";

        IMocksControl ctrl = EasyMock.createStrictControl();

        Random generadorMock = ctrl.createMock(Random.class);
        ClienteWebService clienteMock = ctrl.createMock(ClienteWebService.class);
        EasyMock.expect(generadorMock.nextFloat()).andReturn(esperado_generador);
        EasyMock.expect(clienteMock.obtenerPremio()).andReturn(esperado_cliente);

        ctrl.replay();

        Premio premio = new Premio();
        premio.generador = generadorMock;
        premio.cliente = clienteMock;

        obtenido = premio.compruebaPremio();

        ctrl.verify();

        assertEquals(esperado,obtenido);

    }

    @Test
    public void C2_compruebaPremio() throws ClienteWebServiceException {
        esperado = "No se ha podido obtener el premio";
        esperado_generador = 0.03f;
        esperado_cliente = "entrada final Champions";

        IMocksControl ctrl = EasyMock.createStrictControl();

        Random generadorMock = ctrl.createMock(Random.class);
        ClienteWebService clienteMock = ctrl.createMock(ClienteWebService.class);
        EasyMock.expect(generadorMock.nextFloat()).andReturn(esperado_generador);
        EasyMock.expect(clienteMock.obtenerPremio()).andThrow(new ClienteWebServiceException());

        ctrl.replay();

        Premio premio = new Premio();
        premio.generador = generadorMock;
        premio.cliente = clienteMock;

        obtenido = premio.compruebaPremio();

        ctrl.verify();

        assertEquals(esperado,obtenido);

    }

    @Test
    public void C3_compruebaPremio() throws ClienteWebServiceException {
        esperado = "Sin premio";
        esperado_generador = 0.3f;

        IMocksControl ctrl = EasyMock.createStrictControl();

        Random generadorMock = ctrl.createMock(Random.class);
        EasyMock.expect(generadorMock.nextFloat()).andReturn(esperado_generador);

        ctrl.replay();

        Premio premio = new Premio();
        premio.generador = generadorMock;

        obtenido = premio.compruebaPremio();

        EasyMock.verify(generadorMock);

        assertEquals(esperado,obtenido);

    }


}