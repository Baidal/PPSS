import java.util.Objects;

public class Ticket {
    private float precio_final;

    public void setPrecio_final(float precio_final){ this.precio_final = precio_final; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Float.compare(ticket.precio_final, precio_final) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(precio_final);
    }
}
