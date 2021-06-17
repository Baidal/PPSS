public class ServicioStub implements IService{

    int devolver;

    public void setDevolver(int devolver){
        this.devolver = devolver;
    }

    @Override
    public float consultaPrecio(TipoCoche tipo) {
        return devolver;
    }
}
