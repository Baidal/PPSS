package ppss;

public class OperacionFactory {
    private static Operacion operacion = null;

    public static Operacion create(){
        if(operacion == null){
            return new Operacion();
        }else{
            return operacion;
        }
    }

    public static void setOperacion(Operacion op){
        operacion = op;
    }


}
