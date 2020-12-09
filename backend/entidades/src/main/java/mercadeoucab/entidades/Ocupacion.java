package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ocupacion")
public class Ocupacion extends EntidadBase {

    @Column(name = "nombre")
    private String nombre;

    public Ocupacion(){}
    public Ocupacion(long id){super(id);}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
