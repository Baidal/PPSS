package ppss;

import excepciones.IsbnInvalidoException;
import excepciones.JDBCException;
import excepciones.SocioInvalidoException;

public class Operacion implements IOperacionBO{
    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException {

    }
}
