package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="solicitud")
public class Solicitud extends EntidadBase{

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_marca")
    private Marca marca;

    @OneToMany(
            mappedBy = "solicitud",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private List<TipoSolicitud> tiposSolitud;

    @OneToMany(
            mappedBy = "solicitud",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private List<PresentacionSolicitud> presentacionesSolicitud;

    @OneToMany(
            mappedBy = "solicitud",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private List<SubCategoriaSolicitud> subCategoriasSolicitud;

    @OneToMany(
            mappedBy = "solicitud",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private List<Estudio> estudios;

    public Solicitud(long id) { super(id); }
    public Solicitud(){}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public Marca getMarca() {return marca;}
    public void setMarca(Marca marca) {this.marca = marca;}

    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    public List<TipoSolicitud> getTiposSolitud() {
        return tiposSolitud;
    }

    public void setTiposSolitud(List<TipoSolicitud> tiposSolitud) {
        this.tiposSolitud = tiposSolitud;
    }

    public List<PresentacionSolicitud> getPresentacionesSolicitud() {
        return presentacionesSolicitud;
    }

    public void setPresentacionesSolicitud(List<PresentacionSolicitud> presentacionesSolicitud) {
        this.presentacionesSolicitud = presentacionesSolicitud;
    }

    public List<SubCategoriaSolicitud> getSubCategoriasSolicitud() {
        return subCategoriasSolicitud;
    }

    public void setSubCategoriasSolicitud(List<SubCategoriaSolicitud> subCategoriasSolicitud) {
        this.subCategoriasSolicitud = subCategoriasSolicitud;
    }

    public List<Estudio> getEstudios() {
        return estudios;
    }

    public void setEstudios(List<Estudio> estudios) {
        this.estudios = estudios;
    }
}
