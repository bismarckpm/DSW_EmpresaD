package mercadeoucab.dtos;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

public class DtoSolicitud extends DtoBase{

    private String estado;
    private DtoUsuario usuario;
    private DtoMarca marca;
    private DtoTipo tipo;
    private List<DtoTipo> tipos;

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

    public DtoTipo getTipo() {
        return tipo;
    }

    public void setTipo(DtoTipo tipo) {
        this.tipo = tipo;
    }
}
