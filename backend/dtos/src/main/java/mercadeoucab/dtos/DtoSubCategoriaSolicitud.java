package mercadeoucab.dtos;

public class DtoSubCategoriaSolicitud extends DtoBase {

    private DtoSolicitud solicitud;
    private DtoSubCategoria subCategoria;

    public DtoSubCategoriaSolicitud(long id) throws Exception {
        super(id);
    }

    public DtoSubCategoriaSolicitud() {
    }

    public DtoSolicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(DtoSolicitud solicitud) {
        this.solicitud = solicitud;
    }

    public DtoSubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(DtoSubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }
}
