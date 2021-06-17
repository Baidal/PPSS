package ppss.ejercicio2;

public class StubGestorLlamadasTest extends GestorLlamadas{

    int hora;

    public StubGestorLlamadasTest(int hora){
        this.hora = hora;
    }

    @Override
    public Calendario getCalendario() {
        StubCalendario c = new StubCalendario(hora);
        return c;
    }

}
