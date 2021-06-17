package ppss;

import excepciones.IsbnInvalidoException;
import excepciones.JDBCException;
import excepciones.ReservaException;
import excepciones.SocioInvalidoException;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.anyObject;
import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {

    IMocksControl ctrl;
    Reserva reservaMock;
    FactoriaBOs factoriaMock;
    IOperacionBO operacionMock;
    String login,password,socio, isbns[], mensajeEsperado;
    Usuario tipoUsuario;
    ReservaException reservaException;

    @BeforeEach
    void setUp(){
        ctrl = EasyMock.createStrictControl();
        reservaMock = EasyMock.partialMockBuilder(Reserva.class).addMockedMethod("compruebaPermisos").addMockedMethod("getFactoriaBOs").createMock(ctrl);
        factoriaMock = ctrl.createMock(FactoriaBOs.class);
        operacionMock = ctrl.createMock(IOperacionBO.class);
    }

    /**
     * ATENCIÓN FRIENDLY REMINDER: La interfaz IOperacionBO contiene el método void operacionReserva(). Para realizar
     * un mock sobre un método void, realizamos los siguientes pasos:
     *      1.- Cremos el mock de la clase que contiene el método. En nuestro caso es una clase interfaz, pero da igual.
     *          Creamos el mock de esa clase interfaz.
     *      2.- A partir del mock creado, llamamos al método que devuelve void con los parámetros que queramos que reciba.
     *          Sé que es extraño, pero se hace así
     *      3.- Justo después de esto, llamamos a EasyMock.expectLastCall(). Esto hará que easymock espere que se llame al método anterior
     *          en nuestro sut (básicamente es como hacer el típico EasyMock.expect()...)
     *
     * Un ejemplo de esto puede ser la línea 99 de este código.
     */

    @Test
    @Tag("tests1")
    void C1_realizaReserva(){
        login = "xxxx"; password = "xxxx";
        socio = "Pepe";
        isbns = new String[1]; isbns[0] = "22222";
        tipoUsuario = Usuario.BIBLIOTECARIO;
        mensajeEsperado = "ERROR de permisos; ";

        EasyMock.expect(reservaMock.compruebaPermisos(login,password,tipoUsuario)).andReturn(false);

        ctrl.replay();

        reservaException = assertThrows(ReservaException.class,()-> reservaMock.realizaReserva(login,password,socio,isbns));
        assertEquals(mensajeEsperado,reservaException.getMessage());

        ctrl.verify();
    }

    @Test
    @Tag("tests1")
    void C2_realizaReserva(){
        login = "ppss"; password = "ppss";
        socio = "Pepe";
        isbns = new String[2]; isbns[0] = "22222"; isbns[1] = "33333";
        tipoUsuario = Usuario.BIBLIOTECARIO;

        EasyMock.expect(reservaMock.compruebaPermisos(login,password,tipoUsuario)).andReturn(true);
        EasyMock.expect(reservaMock.getFactoriaBOs()).andReturn(factoriaMock);
        EasyMock.expect(factoriaMock.getOperacionBO()).andReturn(operacionMock);

        /*expects*/ assertDoesNotThrow(()->operacionMock.operacionReserva(socio,isbns[0]));
        EasyMock.expectLastCall();
        /*expects*/ assertDoesNotThrow(()->operacionMock.operacionReserva(socio,isbns[1]));
        EasyMock.expectLastCall();

        ctrl.replay();

        assertDoesNotThrow(()-> reservaMock.realizaReserva(login,password,socio,isbns),"El metodo C2_realizaReserva ha lanzado una excepcion insesperada");

        ctrl.verify();
    }

    @Test
    @Tag("tests1")
    void C3_realizaReserva(){
        login = "ppss"; password = "ppss";
        socio = "Pepe";
        isbns = new String[1]; isbns[0] = "11111";
        tipoUsuario = Usuario.BIBLIOTECARIO;
        mensajeEsperado = "ISBN invalido:"+isbns[0]+"; ";

        EasyMock.expect(reservaMock.compruebaPermisos(login,password,tipoUsuario)).andReturn(true);
        EasyMock.expect(reservaMock.getFactoriaBOs()).andReturn(factoriaMock);
        EasyMock.expect(factoriaMock.getOperacionBO()).andReturn(operacionMock);

        /*expects*/ assertDoesNotThrow(()->operacionMock.operacionReserva(socio,isbns[0]));
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());

        ctrl.replay();

        reservaException = assertThrows(ReservaException.class,()-> reservaMock.realizaReserva(login,password,socio,isbns));
        assertEquals(mensajeEsperado,reservaException.getMessage());

        ctrl.verify();
    }

    @Test
    void C4_realizaReserva(){
        login = "ppss"; password = "ppss";
        socio = "Luis";
        isbns = new String[1]; isbns[0] = "22222";
        tipoUsuario = Usuario.BIBLIOTECARIO;
        mensajeEsperado = "SOCIO invalido; ";

        EasyMock.expect(reservaMock.compruebaPermisos(login,password,tipoUsuario)).andReturn(true);
        EasyMock.expect(reservaMock.getFactoriaBOs()).andReturn(factoriaMock);
        EasyMock.expect(factoriaMock.getOperacionBO()).andReturn(operacionMock);

        /*expects*/ assertDoesNotThrow(()->operacionMock.operacionReserva(socio,isbns[0]));
        EasyMock.expectLastCall().andThrow(new SocioInvalidoException());

        ctrl.replay();

        reservaException = assertThrows(ReservaException.class,()-> reservaMock.realizaReserva(login,password,socio,isbns));
        assertEquals(mensajeEsperado,reservaException.getMessage());

        ctrl.verify();
    }

    @Test
    void C5_realizaReserva(){
        login = "ppss"; password = "ppss";
        socio = "Pepe";
        isbns = new String[3]; isbns[0] = "11111"; isbns[1] = "22222"; isbns[2] = "33333";
        tipoUsuario = Usuario.BIBLIOTECARIO;
        mensajeEsperado = "ISBN invalido:"+isbns[0]+"; CONEXION invalida; ";

        EasyMock.expect(reservaMock.compruebaPermisos(login,password,tipoUsuario)).andReturn(true);
        EasyMock.expect(reservaMock.getFactoriaBOs()).andReturn(factoriaMock);
        EasyMock.expect(factoriaMock.getOperacionBO()).andReturn(operacionMock);

        /*expects*/ assertDoesNotThrow(()->operacionMock.operacionReserva(socio,isbns[0]));
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
        /*expects*/ assertDoesNotThrow(()->operacionMock.operacionReserva(socio,isbns[1]));
        EasyMock.expectLastCall();
        /*expects*/ assertDoesNotThrow(()->operacionMock.operacionReserva(socio,isbns[2]));
        EasyMock.expectLastCall().andThrow(new JDBCException());

        ctrl.replay();

        reservaException = assertThrows(ReservaException.class,()-> reservaMock.realizaReserva(login,password,socio,isbns));
        assertEquals(mensajeEsperado,reservaException.getMessage());

        ctrl.verify();
    }

}