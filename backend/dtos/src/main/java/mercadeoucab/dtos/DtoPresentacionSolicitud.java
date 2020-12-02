package mercadeoucab.dtos;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class DtoPresentacionSolicitud extends DtoBase {

    private DtoPresentacion fk_presentacion;
    private DtoSolicitud Dtosolicitud;

    public DtoPresentacionSolicitud(long id)throws Exception { super(id); }
    public DtoPresentacionSolicitud(){}

    public DtoPresentacion getDto_presentacion() {return fk_presentacion;}
    public void setDto_presentacion(DtoPresentacion fk_presentacion) {this.fk_presentacion = fk_presentacion;}

    public DtoSolicitud getDto_solicitud() {return Dtosolicitud;}
    public void setDto_solicitud(DtoSolicitud DTOS) {this.Dtosolicitud = DTOS;}


}
