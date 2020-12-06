package mercadeoucab.dtos;

public class DtoTipoSolicitud extends DtoBase {

    private DtoTipo tipo;
    private DtoSolicitud solicitud;
    public DtoTipoSolicitud(long id) throws Exception {
        super(id);
    }

    public DtoTipo getTipo() {
        return tipo;
    }

    public void setTipo(DtoTipo tipo) {
        this.tipo = tipo;
    }

    public DtoSolicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(DtoSolicitud solicitud) {
        this.solicitud = solicitud;
    }

    public DtoTipoSolicitud() {
    }
}
