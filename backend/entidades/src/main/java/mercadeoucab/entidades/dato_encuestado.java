package mercadeoucab.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dato_encuestado2")
public class dato_encuestado extends EntidadBase {

    @Column(name = "nombre")
    private String nombre;

    public dato_encuestado(){}
    public dato_encuestado(long id){super(id);}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
