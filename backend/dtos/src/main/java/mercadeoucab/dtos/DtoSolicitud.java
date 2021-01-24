package mercadeoucab.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: DtoSolicitud
 */
public class DtoSolicitud extends DtoBase{

    private String estado;
    private DtoUsuario usuario;

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

    /**
     * Name: addSubcategoria
     * @param dtoSubCategoria
     */
    public void addSubcategoria(DtoSubCategoria dtoSubCategoria){
        if(this.subCategorias == null)
            this.subCategorias = new ArrayList<>();
        this.subCategorias.add(dtoSubCategoria);
    }

    /**
     * Name: addPresentacion
     * @param dtoPresentacion
     */
    public void addPresentacion(DtoPresentacion dtoPresentacion){
        if(this.presentaciones == null)
            this.presentaciones = new ArrayList<>();
        this.presentaciones.add(dtoPresentacion);
    }

    /**
     * Name: addTipo
     * @param dtoTipo
     */
    public void addTipo(DtoTipo dtoTipo){
        if(this.tipos == null)
            this.tipos = new ArrayList<>();
        this.tipos.add(dtoTipo);
    }
}
