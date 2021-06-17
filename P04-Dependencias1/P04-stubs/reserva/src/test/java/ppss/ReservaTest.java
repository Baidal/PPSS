package ppss;

import org.junit.jupiter.api.Test;
import ppss.excepciones.ReservaException;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {
    String login, password,socio, isbns[], esperado, isbns_entrada[];

    @Test
    void C1_realizaReserva(){
        login="xxxx";
        password="xxxx";
        socio="Luis";
        isbns = new String[1];
        isbns[0] = "11111";
        esperado="ERROR de permisos; ";

        ReservaStub reserva = new ReservaStub();
        reserva.setPermisos(false);

        ReservaException excepcion = assertThrows(ReservaException.class, ()->reserva.realizaReserva(login,password,socio,isbns));

        assertEquals(esperado,excepcion.getMessage());

    }

    @Test
    void C2_realizaReserva() {
        login = "ppss";
        password = "ppss";
        socio = "Luis";
        isbns = new String[2];
        isbns[0] = "11111"; isbns[1] = "22222";
        esperado = "ERROR de permisos; ";

        ReservaStub reserva = new ReservaStub();
        reserva.setPermisos(true);

        OperacionStub stub = new OperacionStub();
        stub.setSocio("Luis");
        stub.setIsbns(isbns);
        stub.setConexion(true);

        OperacionFactory.setOperacion(stub);

        assertDoesNotThrow(()->reserva.realizaReserva(login,password,socio,isbns),"Ha lanzado una excepción. No debería.");
    }

    @Test
    void C3_realizaReserva() {
        login = "ppss";
        password = "ppss";
        socio = "Luis";
        isbns = new String[2];
        isbns[0] = "11111"; isbns[1] = "22222";
        isbns_entrada = new String[1];
        isbns_entrada[0] = "33333";
        esperado = "ISBN invalido:33333; ";


        ReservaStub reserva = new ReservaStub();
        reserva.setPermisos(true);

        OperacionStub stub = new OperacionStub();
        stub.setSocio("Luis");
        stub.setIsbns(isbns);
        stub.setConexion(true);

        OperacionFactory.setOperacion(stub);

        ReservaException excepcion = assertThrows(ReservaException.class, ()->reserva.realizaReserva(login,password,socio,isbns_entrada));

        assertEquals(esperado,excepcion.getMessage());
    }

    @Test
    void C4_realizaReserva() {
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[2];
        isbns[0] = "11111"; isbns[1] = "22222";
        isbns_entrada = new String[1];
        isbns_entrada[0] = "11111";
        esperado = "SOCIO invalido; ";


        ReservaStub reserva = new ReservaStub();
        reserva.setPermisos(true);

        OperacionStub stub = new OperacionStub();
        stub.setSocio("Luis");
        stub.setIsbns(isbns);
        stub.setConexion(true);

        OperacionFactory.setOperacion(stub);

        ReservaException excepcion = assertThrows(ReservaException.class, ()->reserva.realizaReserva(login,password,socio,isbns_entrada));

        assertEquals(esperado,excepcion.getMessage());
    }

    @Test
    void C5_realizaReserva() {
        login = "ppss";
        password = "ppss";
        socio = "Luis";
        isbns = new String[2];
        isbns[0] = "11111"; isbns[1] = "22222";
        isbns_entrada = new String[1];
        isbns_entrada[0] = "11111";
        esperado = "CONEXION invalida; ";


        ReservaStub reserva = new ReservaStub();
        reserva.setPermisos(true);

        OperacionStub stub = new OperacionStub();
        stub.setSocio("Luis");
        stub.setIsbns(isbns);
        stub.setConexion(false);

        OperacionFactory.setOperacion(stub);

        ReservaException excepcion = assertThrows(ReservaException.class, ()->reserva.realizaReserva(login,password,socio,isbns_entrada));

        assertEquals(esperado,excepcion.getMessage());
    }
}