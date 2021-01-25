package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Presentacion")
public class Presentacion extends EntidadBase {

    @Column(name = "cantidad")
    private String cantidad;

    @Column(name = "tipo")
    private String tipo;

    @ManyToOne()
    @JoinColumn(name = "tipo_id")
    private Tipo fk_tipo;

    public Presentacion(long id) { super(id); }
    public Presentacion(){}

    public String getCantidad() { return cantidad; }
    public void setCantidad(String cantidad) { this.cantidad = cantidad; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Tipo getFk_tipo() {
        return fk_tipo;
    }

    public void setFk_tipo(Tipo fk_tipo) {
        this.fk_tipo = fk_tipo;
    }
}
