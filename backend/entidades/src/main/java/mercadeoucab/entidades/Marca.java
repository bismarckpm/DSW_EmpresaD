package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="marca")
public class Marca extends EntidadBase {

    @Column(name="nombre")
    private String nombre;

    public Marca(long id) { super(id); }
    public Marca(){}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

}
