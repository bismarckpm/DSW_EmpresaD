package mercadeoucab.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="tipo")
public class Tipo extends EntidadBase{

    @Column( name = "nombre")
    private String nombre;

    public Tipo(long id) {
        super(id);
    }

    public Tipo() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
