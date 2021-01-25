package mercadeoucab.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: DtoSolicitud
 */
public class DtoSolicitud extends DtoBase{

    private String estado;
    private String marca;
    private String comentarios;
    private DtoUsuario usuario;


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
     * Name: addPresentacion
     * @param dtoPresentacion
     */
    public void addPresentacion(DtoPresentacion dtoPresentacion){
        if(this.presentaciones == null)
            this.presentaciones = new ArrayList<>();
        this.presentaciones.add(dtoPresentacion);
    }

    /**
     * Name: getMarca
     * @return marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Name: setMarca
     * @param marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Name: getComentarios
     * @return comentarios
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * Name: setComentarios
     * @param comentarios
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
