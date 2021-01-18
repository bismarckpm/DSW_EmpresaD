package mercadeoucab.dtos;

import java.util.List;

public class DtoSolicitud extends DtoBase{

    private String estado;
    private DtoUsuario usuario;
    private DtoMarca marca;

    private List<DtoTipo> tipos;
    private List<DtoSubCategoria> subCategorias;
    private List<DtoPresentacion> presentaciones;

    public DtoSolicitud(long id)throws Exception{ super(id); }
    public DtoSolicitud(){}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public DtoMarca getMarca() {return marca;}
    public void setMarca(DtoMarca marca) {this.marca = marca;}

    public DtoUsuario getUsuario() {return usuario;}
    public void setUsuario(DtoUsuario usuario) {this.usuario = usuario;}

    public List<DtoTipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<DtoTipo> tipos) {
        this.tipos = tipos;
    }

    public List<DtoSubCategoria> getSubCategorias() {
        return subCategorias;
    }

    public void setSubCategorias(List<DtoSubCategoria> subCategorias) {
        this.subCategorias = subCategorias;
    }

    public List<DtoPresentacion> getPresentaciones() {
        return presentaciones;
    }

    public void setPresentaciones(List<DtoPresentacion> presentaciones) {
        this.presentaciones = presentaciones;
    }
}
