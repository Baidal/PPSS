import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarioStub extends Calendario{

    /**
     * ArrayList que contiene los dias No festivos a comprobar. Por defecto son todos
     */
    ArrayList<LocalDate> diasNoFestivo;
    /**
     * ArrayList que contiene los días que tienen que devolver una excepción
     */
    ArrayList<LocalDate> diasExcepcion;
    /**
     * Arraylist que contiene los dias que son festivos
     */
    ArrayList<LocalDate> diasFestivo;

    /**
     * Inicializa diasNoFestivo a partir de fechaInicio, hasta numDias
     * @param fechaInicio
     * @param numDias
     */
    public CalendarioStub(LocalDate fechaInicio, int numDias){
        diasFestivo = new ArrayList<>();
        diasNoFestivo = new ArrayList<>();
        diasExcepcion = new ArrayList<>();
        for(int i = 0; i < numDias; i++)
            diasNoFestivo.add(fechaInicio.plusDays((long)i));
    }

    /**
     * Añade un dia la lista de los dias que han de devolver la excepción, y quita
     * ese día de diasExcepcion
     * @param dia
     */
    public void addDiaExcepcion(String dia){
        diasExcepcion.add(LocalDate.parse(dia));
        diasNoFestivo.remove(LocalDate.parse(dia));
    }

    /**
     * Añade un dia la lista de los dias que han de devolver la excepción, y quita
     * ese día de diasExcepcion
     * @param dia
     */
    public void addDiaFestivo(String dia){
        diasFestivo.add(LocalDate.parse(dia));
        diasNoFestivo.remove(LocalDate.parse(dia));
    }


    @Override
    public boolean es_festivo(LocalDate date) throws CalendarioException{
        if(diasNoFestivo.contains(date)){
            return false;
        }else if(diasFestivo.contains(date)){
            return true;
        }else{
            throw new CalendarioException();
        }


    }


}

