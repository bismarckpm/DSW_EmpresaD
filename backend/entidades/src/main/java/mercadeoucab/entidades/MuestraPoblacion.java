package mercadeoucab.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "muestra_poblacion2")
public class MuestraPoblacion extends EntidadBase{

    @Column(name = "nombre")
    private String nombre;

    public MuestraPoblacion(){}
    public MuestraPoblacion(long id){super(id);}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
