package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

public class OperacionStub extends Operacion{

    String isbns[], socio;

    boolean conexion;

    public void setIsbns(String[] isbns){
        this.isbns = isbns;
    }

    public void setSocio(String socio){
        this.socio = socio;
    }

    public void setConexion(boolean conexion){
        this.conexion = conexion;
    }

    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException {
        if(!socio.equals(this.socio)){
            throw new SocioInvalidoException();
        }else if(!conexion){
            throw new JDBCException();
        }else{
            boolean existe = false;
            for(String isbn_comprobar : isbns){
                if(isbn_comprobar.equals(isbn))
                    existe = true;
            }

            if(!existe)
                throw new IsbnInvalidoException();

        }

    }
}
