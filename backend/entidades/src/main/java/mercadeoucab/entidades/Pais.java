package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "Pais" )
public class Pais extends EntidadBase{

    public Pais(long id) {
        super(id);
    }

    public Pais() {
    }

    @Column(name = "nombre")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
