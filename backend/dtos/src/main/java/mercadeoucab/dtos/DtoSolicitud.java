package mercadeoucab.dtos;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class DtoSolicitud extends DtoBase{

    private String estado;
    private DtoUsuario usuario;
    private DtoMarca marca;

    public DtoSolicitud(long id)throws Exception{ super(id); }
    public DtoSolicitud(){}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public DtoMarca getMarca() {return marca;}
    public void setMarca(DtoMarca marca) {this.marca = marca;}

    public DtoUsuario getUsuario() {return usuario;}
    public void setUsuario(DtoUsuario usuario) {this.usuario = usuario;}



}
