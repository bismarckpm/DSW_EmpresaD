package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "presentacion")
public class Presentacion extends EntidadBase {

    @Column(name = "cantidad")
    private String cantidad;

    @Column(name = "tipo")
    private String tipo;

    public Presentacion(long id) { super(id); }
    public Presentacion(){}

    public String getCantidad() { return cantidad; }
    public void setCantidad(String cantidad) { this.cantidad = cantidad; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
