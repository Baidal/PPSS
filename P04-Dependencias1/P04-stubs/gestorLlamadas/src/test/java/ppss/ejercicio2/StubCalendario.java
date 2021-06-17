package ppss.ejercicio2;

public class StubCalendario extends Calendario{

    int hora;

    @Override
    public int getHoraActual() {
        return hora;
    }

    public StubCalendario(int hora){
        this.hora = hora;
    }

}
