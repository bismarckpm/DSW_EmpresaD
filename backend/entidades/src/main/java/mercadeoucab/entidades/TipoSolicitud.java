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

    public TipoSolicitud(long id) {
        super(id);
    }

    public TipoSolicitud() {
    }
}
