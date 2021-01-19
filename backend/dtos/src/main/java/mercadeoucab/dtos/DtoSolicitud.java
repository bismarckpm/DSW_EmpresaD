package mercadeoucab.dtos;

import java.util.List;

/**
 * Name: DtoSolicitud
 */
public class DtoSolicitud extends DtoBase{

    private String estado;
    private DtoUsuario usuario;
    private DtoMarca marca;

    private List<DtoTipo> tipos;
    private List<DtoSubCategoria> subCategorias;
    private List<DtoPresentacion> presentaciones;

    /**
     * Name: DtoSolicitud
     * Description: Constructor a partir de id
     * @param id
     * @throws Exception
     */
    public DtoSolicitud(long id)throws Exception{ super(id); }

    /**
     * Name: DtoSolicitud
     * Description: Constructor vacio
     */
    public DtoSolicitud(){}

    /**
     * Name: getEstado
     * @return estado
     */
    public String getEstado() {return estado;}

    /**
     * Name: setEstado
     * @param estado
     */
    public void setEstado(String estado) {this.estado = estado;}

    /**
     * Name: getMarca
     * @return marca
     */
    public DtoMarca getMarca() {return marca;}

    /**
     * Name: setMarca
     * @param marca
     */
    public void setMarca(DtoMarca marca) {this.marca = marca;}

    /**
     * Name: getUsuario
     * @return usuario
     */
    public DtoUsuario getUsuario() {return usuario;}

    /**
     * Name: setUsuario
     * @param usuario
     */
    public void setUsuario(DtoUsuario usuario) {this.usuario = usuario;}

    /**
     * Name: getTipos
     * @return tipos
     */
    public List<DtoTipo> getTipos() {
        return tipos;
    }

    /**
     * Name: setTipos
     * @param tipos
     */
    public void setTipos(List<DtoTipo> tipos) {
        this.tipos = tipos;
    }

    /**
     * Name: getSubCategorias
     * @return subCategorias
     */
    public List<DtoSubCategoria> getSubCategorias() {
        return subCategorias;
    }

    /**
     * Name: setSubCategorias
     * @param subCategorias
     */
    public void setSubCategorias(List<DtoSubCategoria> subCategorias) {
        this.subCategorias = subCategorias;
    }

    /**
     * Name: getPresentaciones
     * @return presentaciones
     */
    public List<DtoPresentacion> getPresentaciones() {
        return presentaciones;
    }

    /**
     * Name: setPresentaciones
     * @param presentaciones
     */
    public void setPresentaciones(List<DtoPresentacion> presentaciones) {
        this.presentaciones = presentaciones;
    }
}
