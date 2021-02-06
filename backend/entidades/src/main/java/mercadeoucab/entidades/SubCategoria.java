package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="sub_categoria")
public class SubCategoria extends EntidadBase {

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne()
    @JoinColumn(name = "fk_categoria")
    private Categoria categoria;

    @OneToMany( mappedBy = "sub_categoria_id", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<Tipo> tipoList;

    public SubCategoria(long id) {
        super(id);
    }

    public SubCategoria() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Tipo> getTipoList() { return tipoList; }

    public void setTipoList(List<Tipo> tipoList) { this.tipoList = tipoList; }

    public void addTipo(Tipo tipo){
        if ( this.tipoList == null )
            this.tipoList = new ArrayList<>();
        this.tipoList.add(tipo);
    }
}
