package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Marca")
public class Marca extends EntidadBase {

    @Column(name="nombre")
    private String nombre;

    @OneToMany(mappedBy = "marca", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Solicitud> solicitud;

    public Marca(long id) { super(id); }
    public Marca(){}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public List<Solicitud> getSolicitud() {return solicitud;}
    public void setSolicitud(List<Solicitud> solicitud) {this.solicitud = solicitud;}
}
