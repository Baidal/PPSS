package ppss;

public class ReservaStub extends Reserva{

    boolean permisos;

    public void setPermisos(boolean permisos){
        this.permisos = permisos;
    }

    @Override
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu){
        return  permisos;
    }

}
