package ppss;

import excepciones.IsbnInvalidoException;
import excepciones.JDBCException;
import excepciones.SocioInvalidoException;

public interface IOperacionBO {
    public void operacionReserva(String socio, String isbn)
            throws IsbnInvalidoException, JDBCException, SocioInvalidoException;
}