package mercadeoucab.entidades;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="Presentacion_Solicitud")

public class PresentacionSolicitud extends EntidadBase{

    @ManyToOne
    @JoinColumn(name = "fk_presentacion")
    private Presentacion fk_presentacion;

    @ManyToOne
    @JoinColumn(name = "fk_solicitud")
    private Solicitud fk_solicitud;

    public PresentacionSolicitud(long id) { super(id); }
    public PresentacionSolicitud(){}

    public Presentacion getFk_presentacion() {return fk_presentacion;}
    public void setFk_presentacion(Presentacion fk_presentacion) {this.fk_presentacion = fk_presentacion;}

    public Solicitud getFk_solicitud() {return fk_solicitud;}
    public void setFk_solicitud(Solicitud fk_solicitud) {this.fk_solicitud = fk_solicitud;}
}
