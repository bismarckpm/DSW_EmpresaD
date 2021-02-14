package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "categoria" )
public class Categoria extends EntidadBase{

    public Categoria(long id) {
        super(id);
    }

    public Categoria() { }

    @Column(name = "nombre")
    private String nombre;

    @OneToMany( mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<SubCategoria> subCategorias;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<SubCategoria> getSubCategorias() { return subCategorias; }

    public void setSubCategorias(List<SubCategoria> subCategorias) { this.subCategorias = subCategorias; }

    public void addSubCategoria(SubCategoria subCategoria){
        if(this.subCategorias == null)
            this.subCategorias = new ArrayList<>();
        this.subCategorias.add(subCategoria);
    }
}
