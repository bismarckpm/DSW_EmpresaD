package mercadeoucab.entidades;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="sub_categoria_solicitud")
public class SubCategoriaSolicitud extends EntidadBase {

    @ManyToOne
    @JoinColumn(name = "fk_solicitud")
    private Solicitud solicitud;

    public SubCategoriaSolicitud(long id) {
        super(id);
    }

    public SubCategoriaSolicitud() {
    }
}
