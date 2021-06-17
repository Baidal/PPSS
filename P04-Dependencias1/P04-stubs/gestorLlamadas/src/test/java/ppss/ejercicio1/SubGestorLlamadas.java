package ppss.ejercicio1;

public class SubGestorLlamadas extends GestorLlamadas {

    int hora;

    public SubGestorLlamadas(int hora){
        this.hora = hora;
    }

    @Override
    public int getHoraActual(){
        return hora;
    }

}
