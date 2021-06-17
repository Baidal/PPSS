public class AlquilaCocheTestable extends  AlquilaCoches{

    IService servicio;

    @Override
    protected IService getServicio(){

        return servicio;
    }

    public void setServicio(IService servicio){
        this.servicio = servicio;
    }
}
