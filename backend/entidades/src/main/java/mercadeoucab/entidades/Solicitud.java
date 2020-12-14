package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="solicitud")
@NamedQueries({
        @NamedQuery(
                name = "solicitudes_de_un_cliente",
                query = "select s from Solicitud s where s.usuario = :usuario"
        )
})
public class Solicitud extends EntidadBase{

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_marca")
    private Marca marca;

    @JoinTable(
            name = "tipo_solicitud",
            joinColumns = @JoinColumn(name = "fk_solicitud", nullable = false),
            inverseJoinColumns = @JoinColumn(name="fk_tipo", nullable = false))
    @ManyToMany()
    private List<Tipo> tipos;

    @JoinTable(
            name = "sub_categoria_solicitud",
            joinColumns = @JoinColumn(name = "fk_solicitud", nullable = false),
            inverseJoinColumns = @JoinColumn(name="fk_sub_categoria", nullable = false))
    @ManyToMany()
    private List<SubCategoria> subCategorias;

    @JoinTable(
            name = "presentacion_solicitud",
            joinColumns = @JoinColumn(name = "fk_solicitud", nullable = false),
            inverseJoinColumns = @JoinColumn(name="fk_presentacion", nullable = false))
    @ManyToMany()
    private List<Presentacion> presentaciones;


    public Solicitud(long id) { super(id); }
    public Solicitud(){}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public Marca getMarca() {return marca;}
    public void setMarca(Marca marca) {this.marca = marca;}

    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    public void addTipo(Tipo tipo){
        if(this.tipos == null)
            this.tipos = new ArrayList<>();
        this.tipos.add(tipo);
    }

    public void addSubCategoria(SubCategoria subCategoria){
        if(this.subCategorias == null)
            this.subCategorias = new ArrayList<>();
        this.subCategorias.add(subCategoria);
    }

    public void addPresentacion(Presentacion presentacion){
        if(this.presentaciones == null)
            this.presentaciones = new ArrayList<>();
        this.presentaciones.add(presentacion);
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }

    public List<SubCategoria> getSubCategorias() {
        return subCategorias;
    }

    public void setSubCategorias(List<SubCategoria> subCategorias) {
        this.subCategorias = subCategorias;
    }

    public List<Presentacion> getPresentaciones() {
        return presentaciones;
    }

    public void setPresentaciones(List<Presentacion> presentaciones) {
        this.presentaciones = presentaciones;
    }
}
