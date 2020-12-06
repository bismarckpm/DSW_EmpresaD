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

    @ManyToOne
    @JoinColumn(name = "fk_sub_categoria")
    private SubCategoria subCategoria;

    public SubCategoriaSolicitud(long id) {
        super(id);
    }

    public SubCategoriaSolicitud() {
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public SubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }
}
