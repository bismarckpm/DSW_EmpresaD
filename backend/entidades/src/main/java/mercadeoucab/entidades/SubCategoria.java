package mercadeoucab.entidades;

import javax.persistence.*;

@Entity
@Table(name ="sub_categoria")
public class SubCategoria extends EntidadBase {

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne()
    @JoinColumn(name = "fk_categoria")
    private Categoria categoria;

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
}
