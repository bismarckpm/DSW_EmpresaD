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

    @OneToMany(mappedBy = "fk_presentacion", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Presentacion_Solicitud> presentacionSolicitud;

    public Presentacion(long id) { super(id); }
    public Presentacion(){}

    public String getCantidad() { return cantidad; }
    public void setCantidad(String cantidad) { this.cantidad = cantidad; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public List<Presentacion_Solicitud> getPresentacionSolicitud() { return presentacionSolicitud; }
    public void setPresentacionSolicitud(List<Presentacion_Solicitud> presentacionSolicitud) { this.presentacionSolicitud = presentacionSolicitud; }
}
