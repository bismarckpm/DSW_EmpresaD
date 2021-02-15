package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: CAtegoria
 */
@Entity
@Table( name = "categoria" )
public class Categoria extends EntidadBase{
    /**
     * Name: Categoria
     * Description: constructor con id
     * @param id
     */
    public Categoria(long id) {
        super(id);
    }

    /**
     * Name: Categoria
     * Description: COnstructor vacio
     */
    public Categoria() { }

    @Column(name = "nombre")
    private String nombre;

    @OneToMany( mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<SubCategoria> subCategorias;

    /**
     * Name: getNombre
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Name: setNombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Name: getSubCategorias
     * @return
     */
    public List<SubCategoria> getSubCategorias() { return subCategorias; }

    /**
     * Name: setSubCategorias
     * @param subCategorias
     */
    public void setSubCategorias(List<SubCategoria> subCategorias) { this.subCategorias = subCategorias; }

    /**
     * Name: addSubCategoria
     * @param subCategoria
     */
    public void addSubCategoria(SubCategoria subCategoria){
        if(this.subCategorias == null)
            this.subCategorias = new ArrayList<>();
        this.subCategorias.add(subCategoria);
    }
}
