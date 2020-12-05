package mercadeoucab.dtos;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

public class DtoSolicitud extends DtoBase{

    private String estado;
    private DtoUsuario usuario;
    private DtoMarca marca;
    private List<DtoSubCategoriaSolicitud> subCategoriasSolicitud;
    private List<DtoTipoSolicitud> tiposSolitud;
    private List<DtoEstudio> estudios;
    private List<DtoPresentacionSolicitud> presentacionesSolicitud;

    public DtoSolicitud(long id)throws Exception{ super(id); }
    public DtoSolicitud(){}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public DtoMarca getMarca() {return marca;}
    public void setMarca(DtoMarca marca) {this.marca = marca;}

    public DtoUsuario getUsuario() {return usuario;}
    public void setUsuario(DtoUsuario usuario) {this.usuario = usuario;}

    public List<DtoSubCategoriaSolicitud> getSubCategoriasSolicitud() {
        return subCategoriasSolicitud;
    }

    public void setSubCategoriasSolicitud(List<DtoSubCategoriaSolicitud> subCategoriasSolicitud) {
        this.subCategoriasSolicitud = subCategoriasSolicitud;
    }

    public List<DtoTipoSolicitud> getTiposSolitud() {
        return tiposSolitud;
    }

    public void setTiposSolitud(List<DtoTipoSolicitud> tiposSolitud) {
        this.tiposSolitud = tiposSolitud;
    }

    public List<DtoEstudio> getEstudios() {
        return estudios;
    }

    public void setEstudios(List<DtoEstudio> estudios) {
        this.estudios = estudios;
    }

    public List<DtoPresentacionSolicitud> getPresentacionesSolicitud() {
        return presentacionesSolicitud;
    }

    public void setPresentacionesSolicitud(List<DtoPresentacionSolicitud> presentacionesSolicitud) {
        this.presentacionesSolicitud = presentacionesSolicitud;
    }
}
