package ppss;

import java.util.Objects;

public class Tramo {
    private int longitud;
    private int origen;

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setDuracion(int longitud) {
        this.longitud = longitud;
    }

    public Tramo(){longitud = 0; origen = 0;}

    public Tramo(int longitud, int origen){
        this.longitud = longitud;
        this.origen = origen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tramo)) return false;
        Tramo tramo = (Tramo) o;
        return getLongitud() == tramo.getLongitud() && getOrigen() == tramo.getOrigen();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLongitud(), getOrigen());
    }

    @Override
    public String toString() {
        return "Tramo{" +
                "longitud=" + longitud +
                ", origen=" + origen +
                '}';
    }
}
