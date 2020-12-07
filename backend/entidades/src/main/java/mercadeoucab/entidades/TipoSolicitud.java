package mercadeoucab.entidades;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="tipo_solicitud")
public class TipoSolicitud extends EntidadBase {

    @ManyToOne
    @JoinColumn(name = "fk_solicitud")
    private Solicitud solicitud;

    @ManyToOne
    @JoinColumn(name = "fk_tipo")
    private Tipo tipo;

    public TipoSolicitud(long id) {
        super(id);
    }

    public TipoSolicitud() {
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
