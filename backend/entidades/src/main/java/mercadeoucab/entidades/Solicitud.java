package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.ArrayList;
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

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }
}
