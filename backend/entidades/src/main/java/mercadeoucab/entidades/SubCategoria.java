package mercadeoucab.entidades;

import javax.persistence.*;

@Entity
@Table(name ="sub_categoria")
public class SubCategoria extends EntidadBase {

    @Column(name = "nombre")
    private String nombre;

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

}